package com.plf.learn.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author Panlf
 * @date 2019/6/5
 */
public class EncryptionPassword {

    public static void main(String[] args) {
        /**
         * 加密密码
         */
        String password = "123456";

        /**
         * 加密算法
         */
        String algorithmName = "MD5";

        /**
         *  盐值
         */
        Object salt = "1a2b3c";
        /**
         * 加密次数
         */
        int hashIterations = 1024;

        SimpleHash simpleHash = new SimpleHash(algorithmName,password,salt,hashIterations);
        /**
         * 加密后密码
         */
        System.out.println(simpleHash);
    }
}
