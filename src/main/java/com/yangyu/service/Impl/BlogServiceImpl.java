package com.yangyu.service.Impl;



import com.yangyu.dto.BlogDto;
import com.yangyu.mapper.BlogMapper;

import com.yangyu.po.Blog;
import com.yangyu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> findBlog(int pageNumber,int num,Long userId) {
        int n1=pageNumber*num;
        return blogMapper.findBlog(n1,num,userId);
    }


    @Override
    public void save(Blog blog) {
        blogMapper.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogMapper.delete(id);
    }


    @Override
    public BlogDto getById(Long id) {
        return blogMapper.getById(id);
    }

    @Override
    public void update(Blog blog) {
        blog.setUpdateTime(new Date());
        blogMapper.update(blog);
    }

    @Override
    public int deleteType(Long typeId) {
        return blogMapper.updateType(typeId);
    }

    @Override
    public List<Blog> findBlogByType(Long id) {
        return blogMapper.findBlogByType(id);
    }

    @Override
    public List<Blog> findBlogByTag(Long id) {
        return blogMapper.findBlogByTag(id);
    }

    @Override
    public Integer count(Long userId){
        return blogMapper.count(userId);
    }

    @Override
    public List<Blog> findHotBlog() {
        return blogMapper.findHotBlog();
    }

    @Override
    public List<BlogDto> findByKeywords(String keywords, Long userId) {
        return blogMapper.findByKeywords(keywords,userId);
    }

    @Override
    public List<BlogDto> findAllByKeywords(String keywords,int pageNum) {
        return blogMapper.findAllByKeywords(keywords,(pageNum-1)*10);
    }
}
