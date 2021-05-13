package com.yangyu.mapper;

import com.yangyu.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Arya
 */
@Mapper
public interface BlogTagMapper {
    List<Tag> findByBlogId(Long blogId);
    int addBlogTag(@Param("blogId")Long blogId,@Param("tagId")Long tagId);
    int updateTag(Long tagId);
    int deleteBlogTag(Long id);
}
