package com.plf.learn.log.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Panlf
 * @date 2020/3/18
 */
@Data
public class LogMessage {
    private String id;
    /**
     * 操作描述
     */
    private String description;
    private String ip;
    /**
     * 操作人
     */
    private String operateName;
    private String uri;
    private String url;
    /**
     * 操作花费的时间
     */
    private Long duration;

    private Date startTime;

    private Date endTime;
}
