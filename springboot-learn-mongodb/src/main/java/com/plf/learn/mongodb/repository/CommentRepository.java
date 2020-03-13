package com.plf.learn.mongodb.repository;

import com.plf.learn.mongodb.bean.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Panlf
 * @date 2020/3/2
 */
public interface CommentRepository extends MongoRepository<Comment,String>{

    public Page<Comment>  findByParentid(String parentid, Pageable pageable);
}
