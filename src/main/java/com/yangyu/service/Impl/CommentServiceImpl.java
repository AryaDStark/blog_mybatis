package com.yangyu.service.Impl;

import com.yangyu.dto.CommentDto;
import com.yangyu.mapper.CommentMapper;
import com.yangyu.po.Comment;
import com.yangyu.service.CommentService;
import com.yangyu.util.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDto> findCommentsByBlogId(Long id) {
        return commentMapper.findByBlogId(id);
    }

    @Override
    public Comment findById(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> findNoParentComment(Long id){
//        CommentUtil commentUtil=new CommentUtil();
//        List<Comment> comments=commentUtil.find(id);

        List<Comment> comments=this.find(id);
        return comments;
    }

    @Override
    public void save(Comment comment) {

        commentMapper.save(comment);
    }

    public List<Comment> find(Long blogId){
        //无父评论 的评论集合
        List<Comment> parentComments=commentMapper.findNoParentComment(blogId);
        //所有评论
        List<CommentDto> allComments=commentMapper.findByBlogId(blogId);
        //找出无父评论的评论 的子评论集合 并赋 子评论集合 其父评论属性
        for (Comment parentComment:parentComments){
            List<Comment> replies=new ArrayList<Comment>();
            for (CommentDto commentDto:allComments){
                if (commentDto.getParentCommentId().equals(parentComment.getId())){
                    Comment comment=commentMapper.findById(commentDto.getId());
                    replies.add(comment);
                    replies= findSon(comment,blogId,replies);
                }
            }

            parentComment.setReplyComment(replies);
        }
        return parentComments;
    }

    public List<Comment> findSon(Comment sonComment,Long blogId,List<Comment> replies){
        //所有评论
        List<CommentDto> allComments=commentMapper.findByBlogId(blogId);
       for (CommentDto commentDto:allComments){
           if (commentDto.getParentCommentId().equals(sonComment.getId())){
               Comment comment=commentMapper.findById(commentDto.getId());
               replies.add(comment);
               findSon(comment,blogId,replies);
           }
       }
       return replies;
    }
}
