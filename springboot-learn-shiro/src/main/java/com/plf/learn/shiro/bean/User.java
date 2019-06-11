package com.plf.learn.shiro.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Panlf
 * @date 2019/6/5
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -9043123869071313256L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private String permissions;

    @Column
    private String roles;

}
