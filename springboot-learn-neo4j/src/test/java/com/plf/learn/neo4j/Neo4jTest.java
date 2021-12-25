package com.plf.learn.neo4j;

import com.plf.learn.neo4j.dao.MinFamilyRepository;
import com.plf.learn.neo4j.entity.MinFamily;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2021/12/25
 */
@SpringBootTest(classes = Neo4jApplication.class)
public class Neo4jTest {

    @Resource
    private MinFamilyRepository minFamilyRepository;

    @Test
    public void testCreate(){
        MinFamily minFamily = new MinFamily();
        minFamily.setName("赵素敏");
        minFamilyRepository.save(minFamily);
    }

    @Test
    public void testDelete(){
        minFamilyRepository.deleteById(10L);
    }

    @Test
    public void testUpdate(){
        MinFamily minFamily = new MinFamily();
        minFamily.setId(5L);
        minFamily.setName("金家骏");
        minFamilyRepository.save(minFamily);
    }

    @Test
    public void testCreateRelation(){
        minFamilyRepository.createRelation("陈天福","喜欢","赵素敏");
    }
    @Test
    public void findAll(){
        System.out.println( minFamilyRepository.findAll());
    }

    @Test
    public void getByName(){
        System.out.println( minFamilyRepository.findByName("陈天福","喜欢"));
    }
}

