package com.plf.learn.jwt.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Panlf
 * @date 2019/5/16
 */
@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;
}
