package com.plf.learn.shiro.bean;

import lombok.Data;

/**
 * @author Panlf
 * @date 2019/6/5
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String permissions;

    private String roles;

}
