package com.yangyu.util;

import com.yangyu.dto.CommentDto;
import com.yangyu.mapper.CommentMapper;
import com.yangyu.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommentUtil {

    @Autowired
    CommentMapper commentMapper;

    /**
     * 找出所有无父评论的评论 及其 子评论集合
     * */
    public List<Comment> find(Long blogId){
        //无父评论 的评论集合
        List<Comment> parentComments=commentMapper.findNoParentComment(blogId);
        //所有评论
        List<CommentDto> allComments=commentMapper.findByBlogId(blogId);
        //找出无父评论的评论 的子评论集合 并赋 子评论集合 其父评论属性
        for (Comment parentComment:parentComments){
            List<Comment> replies=new ArrayList<Comment>();
            for (CommentDto commentDto:allComments){
                if (commentDto.getId().equals(parentComment.getId())){
                    Comment comment=commentMapper.findById(commentDto.getId());
                 replies.add(comment);
                }
            }
            parentComment.setReplyComment(replies);
        }
        return parentComments;
    }

}
