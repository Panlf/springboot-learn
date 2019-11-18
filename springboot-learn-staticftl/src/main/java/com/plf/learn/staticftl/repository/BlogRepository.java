package com.plf.learn.staticftl.repository;

import com.plf.learn.staticftl.bean.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Panlf
 * @date 2019/11/18
 */
@Repository
public interface BlogRepository extends MongoRepository<Blog,java.lang.String> {
}
