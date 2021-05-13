package com.yangyu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Arya
 */
@Mapper
public interface BlogTagMapper {
    int addBlogTag(@Param("blogId")Long blogId,@Param("tagId")Long tagId);
    int updateTag(Long tagId);
    int deleteBlogTag(Long id);
}
