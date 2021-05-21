package com.yangyu.web;

import com.yangyu.po.User;
import com.yangyu.service.UserService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author Arya
 */
@RestController
public class AboutMeShow {
    @Autowired
    UserService userService;

    @GetMapping("/seeAboutMe/{id}")
    public Result sam(@PathVariable Long id){
        User user = userService.getById(id);
        user.setPassword("");
        user.setUsername("");
        return Result.ok().data("user",user);
    }
}
