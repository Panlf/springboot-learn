package com.plf.learn.jetcache.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Panlf
 * @date 2019/5/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable{

    private Integer id;

    private String name;

    private Integer age;
}
