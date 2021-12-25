package com.plf.learn.neo4j.dao;

import com.plf.learn.neo4j.entity.MinFamily;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author panlf
 * @date 2021/12/25
 */
@Repository
public interface MinFamilyRepository extends Neo4jRepository<MinFamily,Long> {
    @Query("match (n:minFamily {name:$in}),(m:minFamily {name:$out}) " +
            "create (n)-[:小敏家人物关系{relation:$relation}]->(m)")
    void createRelation(@Param("in") String in, @Param("relation") String relation, @Param("out") String out);

    @Query(" MATCH p = (n:minFamily {name:$name}), (n)-[:小敏家人物关系{relation:$relation}]->(t) return t")
    List<MinFamily> findByName(@Param("name") String name, @Param("relation") String relation);
}
