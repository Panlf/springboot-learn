package com.plf.learn.jwt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Panlf
 * @date 2019/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 业务数据
     */
    private Object data;

    /**
     * 返回描述
     */
    private String msg;

    /**
     * 身份标识
     */
    private String token;

    public static ResponseData result(Integer code,Object data,String msg,String token){
       return new ResponseData(code,data,msg,token);
    }
}
