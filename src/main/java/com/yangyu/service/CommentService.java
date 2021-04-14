package com.yangyu.service;

import com.yangyu.dto.CommentDto;
import com.yangyu.po.Comment;

import java.util.List;

public interface CommentService {


  /**
   * 根据博客id查找对应所有评论
   * @param id
   * @return comment
   **/
    List<CommentDto> findCommentsByBlogId(Long id);

    Comment findById(Long id);

    /**
     * 查找出所有无父评论的评论 及其 子评论
     * @param id
     * @return comment
     * */
    List<Comment> findNoParentComment(Long id);

    void   save(Comment comment);


}
