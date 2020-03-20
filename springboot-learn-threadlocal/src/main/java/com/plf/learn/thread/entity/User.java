package com.plf.learn.thread.entity;

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
public class User {
    private String id;

    private String username;

    private String password;
}
