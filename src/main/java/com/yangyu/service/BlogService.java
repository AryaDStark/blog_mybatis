package com.yangyu.service;


import com.yangyu.dto.BlogDto;
import com.yangyu.po.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BlogService {

   List<BlogDto> findBlog(int pageNumber,int num,Long blogId);

   List<Blog> records(Long userId);

   Long save(Blog blog);

   void delete(Long id);

   BlogDto getById(Long id);

   void update(Blog blog);

   int deleteType(Long typeId);

   List<BlogDto>  findBlogByType(Long id);

   List<BlogDto>  findBlogByTag(Long id);

   Integer count(Long userId);

   Integer countSearch(String keyword);

    List<Blog>           findHotBlog();

   List<BlogDto>   findByKeywords(String keywords, Long userId);

   List<BlogDto>   findAllByKeywords(String keywords,int pageNum);

}
