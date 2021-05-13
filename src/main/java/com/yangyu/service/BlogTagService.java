package com.yangyu.service;

import com.yangyu.po.Tag;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * @author Arya
 */
public interface BlogTagService {
    List<Tag> findTagByBlog(Long blogId);
    int addBlogTag(Long blogId,Long tagId);
    int updateTag(Long tagId);
    int deleteBlogTag(Long id);
}
