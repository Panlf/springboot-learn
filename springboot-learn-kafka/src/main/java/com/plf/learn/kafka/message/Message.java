package com.plf.learn.kafka.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String topic;

    private Integer partition;

    private String content;

    private Date createTime;
}
