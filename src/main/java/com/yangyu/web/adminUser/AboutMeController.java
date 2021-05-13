package com.yangyu.web.adminUser;

import com.yangyu.po.User;
import com.yangyu.service.UserService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Arya
 */
@RestController
@RequestMapping("/admin")
public class AboutMeController {

    @Autowired
    UserService userService;

    @GetMapping("/aboutMe")
    public Result aboutMe(HttpSession session){
        return Result.ok().data("user",session.getAttribute("adminUser"));
    }

    @PostMapping("/changeMe")
    public Result changeMe(@RequestBody User user){
        User userN = new User();
        userN.setUsername(user.getUsername());
        userN.setPassword(user.getPassword());
        userN.setEmail(user.getEmail());
        userN.setNickname(user.getNickname());
        userN.setAvatar(user.getAvatar());
        if (userService.updateUser(userN)>0){
            return Result.ok().data("ok","ok");
        }else {
            return Result.error();
        }

    }

}
