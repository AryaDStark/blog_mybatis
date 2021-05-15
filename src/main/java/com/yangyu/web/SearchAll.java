package com.yangyu.web;

import com.yangyu.service.BlogService;
import com.yangyu.service.UserService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arya
 */
@RestController
public class SearchAll {

    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @GetMapping("/searchAll")
    public Result search(@RequestParam String keyword,@RequestParam int pageNum){

        return Result.ok().data("blogs", blogService.findAllByKeywords(keyword,pageNum))
                .data("total",blogService.countSearch(keyword))
                .data("users",userService.getByName(keyword));
    }
}
