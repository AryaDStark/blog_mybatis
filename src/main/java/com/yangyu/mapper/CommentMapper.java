package com.yangyu.mapper;

import com.yangyu.dto.CommentDto;
import com.yangyu.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface CommentMapper {
    /**
     * 根据blog的id查找该博客对应的评论集合
     *
     * @param id
     * @return List<Comment>
     * */
    List<CommentDto> findByBlogId(@Param("id") Long id);

    /**
     * 根据id查找评论
     *
     * @param id
     * @return Comment
     * */
    Comment findById(Long id);


    /**
     * 根据博客id
     * 查找所有没有父评论的评论
     *
     * @param
     * @return comment
     * */
    List<Comment> findNoParentComment(Long id);


    /**
     * 保存评论
     *
     * @param comment
     * */
    void   save(Comment comment);

}
