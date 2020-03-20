package com.plf.learn.thread.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Panlf
 * @date 2020/3/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer code;
    private String message;
    private Object data;

    public static Message success(String message,Object data){
        return new Message(200,message,data);
    }

    public static Message fail(String message,Object data){
        return new Message(500,message,data);
    }
}
