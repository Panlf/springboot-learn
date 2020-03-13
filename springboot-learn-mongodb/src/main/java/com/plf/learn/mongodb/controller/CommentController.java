package com.plf.learn.mongodb.controller;

import com.plf.learn.mongodb.bean.Comment;
import com.plf.learn.mongodb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Panlf
 * @date 2020/3/2
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/save")
    public String saveComment(Comment comment){
        comment.setCreatedatetime(LocalDateTime.now());
        commentService.saveComment(comment);
        return "success";
    }

    @GetMapping("/page")
    public Page<Comment> findCommentListByParentid(String parentid, int pageNumber, int pageSize){
        return commentService.findCommentListByParentid(parentid,pageNumber-1,pageSize);
    }

    @GetMapping("/incrlikenum")
    public Comment incrLikenum(String id){
        return commentService.updateCommentLikenum(id);
    }
}
