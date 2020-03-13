package com.plf.learn.mongodb.bean;

import lombok.Data;
//import org.springframework.data.mongodb.core.index.CompoundIndex;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Panlf
 * @date 2020/3/2
 */
@Document("comment")
@Data
//复合索引
//@CompoundIndex(def="{'userid':1,'nickname':-1}")
public class Comment {

    //主键标识，该属性的值会自动对应mongodb的主键字段"_id",如果该属性名就叫id，则该注解可以省略，否则必须写
    //@Id
    private String id;
    private String comment;

    /**
     * 添加一个单字段索引
     */
    @Indexed
    private String userid;
    private String nickname;
    private LocalDateTime createdatetime;
    private Integer likenum;
    private Integer replynum;
    private String state;
    private String parentid;
    private String articleid;
}
