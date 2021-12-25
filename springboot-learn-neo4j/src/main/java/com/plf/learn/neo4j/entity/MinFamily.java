package com.plf.learn.neo4j.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

/**
 * @author panlf
 * @date 2021/12/25
 */
@Data
@Node(labels = "minFamily")
@NoArgsConstructor
public class MinFamily {

    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;

    public MinFamily(String name){
        this.name = name;
    }

}
