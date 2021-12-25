package com.plf.learn.neo4j.controllrt;

import com.plf.learn.neo4j.dao.MinFamilyRepository;
import com.plf.learn.neo4j.entity.MinFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2021/12/25
 */
@RestController
public class Neo4jController {
    @Resource
    private MinFamilyRepository minFamilyRepository;

    @GetMapping("create")
    public String testCreate(String name){
        MinFamily minFamily = new MinFamily(name);
        minFamilyRepository.save(minFamily);
        return "success";
    }
}
