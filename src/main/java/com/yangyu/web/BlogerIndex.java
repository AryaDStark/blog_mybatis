package com.yangyu.web;




import com.fasterxml.jackson.databind.ObjectMapper;

import com.yangyu.service.BlogService;
import com.yangyu.service.TagService;
import com.yangyu.service.TypeService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



@Controller
public class BlogerIndex {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    //展示 type
    @GetMapping("/types")
    @ResponseBody
    public Result showIndexTypes(Long userId)  {
        return Result.ok().data("types",typeService.findTopTypes(userId));
    }
    @GetMapping("/tags")
    @ResponseBody
    public Result showIndexTags(Long userId)  {
        return Result.ok().data("tags",tagService.findTopTags(userId));
    }


    //展示 blog
   @GetMapping("/blogPage")
   @ResponseBody
    public Result showBlog(int pageNumber,Long userId){
          if (pageNumber==-1){pageNumber=0;}
         return  Result.ok().data("blogs",blogService.findBlog(pageNumber,10,userId)).data("count",blogService.count(userId));
    }


    //根据views 排热门blog
    @GetMapping("/hotBlogs")
    @ResponseBody
    public Result hotBlog(){
        return Result.ok().data("hotBlogs",blogService.findHotBlog());
    }




}
