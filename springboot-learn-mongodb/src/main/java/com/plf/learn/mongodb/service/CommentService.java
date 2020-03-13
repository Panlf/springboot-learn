package com.plf.learn.mongodb.service;

import com.plf.learn.mongodb.bean.Comment;
import com.plf.learn.mongodb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2020/3/2
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public Page<Comment> findCommentListByParentid(String parentid,int pageNumber,int pageSize){
        return commentRepository.findByParentid(parentid, PageRequest.of(pageNumber,pageSize));
    }

    /**
     * 点赞数加1
     * @param id
     */
    public Comment updateCommentLikenum(String id){
        //查询条件
        Query query = Query.query(Criteria.where("_id").is(id));
        //更新条件
        Update update = new Update();
        update.inc("likenum");
        mongoTemplate.updateFirst(query,update,Comment.class);

        return commentRepository.findById(id).get();
    }

}
