package com.plf.learn.jwt.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.plf.learn.jwt.common.JwtResult;
import io.jsonwebtoken.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Panlf
 * @date 2019/5/14
 */
public class JwtUtils {

    /**
     * 服务器的令牌  - 服务器的key 。用于做加解密的key数据。
     * 如果使用客户端生成的key。当前定义的常量可以不使用
     */
    private static final String JWT_SECRET = "jwt_secret";

    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * token 过期
     */
    private static final int JWT_ERROR_CODE_EXPIRE = 1005;

    /**
     * 验证不通过
     */
    private static final int JWT_ERROR_CODE_FAIL = 1006;

    public static SecretKey generalKey() {
        try {
            //byte[] encodeKey = Base64.decodeBase64(JWT_SECRET);
            byte[] encodeKey = JWT_SECRET.getBytes(StandardCharsets.UTF_8);
            return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 签发JWT，创建token的方法
     *
     * @param id        jwt的唯一身份标识，主要用来作为一次性token，从而回避重发攻击
     * @param iss       jwt签发者
     * @param subject   jwt所面向的用户 payload中记录的public claims。当前环境中就是用户的登录名
     * @param ttlMillis 有效期，单位毫秒
     * @return token token是一次性的。是为一个用户的有效登录周期准备一个token。用户退出或超时，token失效
     */
    public static String createJWT(String id, String iss, String subject, long ttlMillis) {
        //加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //当前时间的日期对象
        Date now = new Date(nowMillis);

        SecretKey secretKey = generalKey();
        //创建JWT的构建器。就是使用指定的信息和加密算法，生成Token的工具
        JwtBuilder builder = Jwts.builder()
                //设置身份标识。就是客户端的唯一标记。如可以使用用户主键、客户端IP、服务器生成的随机数据
                .setId(id)
                .setIssuer(iss)
                .setSubject(subject)
                //签发时间 token生成的时间
                .setIssuedAt(now)
                //设定密匙和算法
                .signWith(signatureAlgorithm, secretKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            // token 失效时间
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        //  生成token
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwtstr
     * @return
     */
    public static JwtResult validateJWT(String jwtstr) {
        JwtResult checkResult = new JwtResult();
        Claims claims = null;

        try {
            claims = parseJWt(jwtstr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(JWT_ERROR_CODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 解析JWT字符
     *
     * @param jwtstr 就是服务器为客户端生成的签名数据，就是token
     * @return
     */
    public static Claims parseJWt(String jwtstr) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtstr)
                //getBody获取的是token中记录的payload数据。就是payload中保存的所有的claims。
                .getBody();
    }

    /**
     * 生成subject信息
     *
     * @param subObj 要转换的对象
     * @return java对象 -> JSON字符串出错时返回null
     */
    public static String generalSubject(Object subObj) {
        try {
            return MAPPER.writeValueAsString(subObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

