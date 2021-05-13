package com.yangyu.service;

/**
 * @author Arya
 */
public interface BlogTagService {
    int addBlogTag(Long blogId,Long tagId);
    int updateTag(Long tagId);
    int deleteBlogTag(Long id);
}
