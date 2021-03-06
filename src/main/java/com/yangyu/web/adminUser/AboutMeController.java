package com.yangyu.web.adminUser;

import com.yangyu.dto.UserDto;
import com.yangyu.po.User;
import com.yangyu.service.CommentService;
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
    @Autowired
    CommentService commentService;

    @GetMapping("/aboutMe")
    public Result aboutMe(HttpSession session){
        return Result.ok().data("user",session.getAttribute("adminUser"));
    }

    @PostMapping("/changeMe")
    public Result changeMe(@RequestBody UserDto userDto,HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
        User userN = new User();
        userN.setId(userId);
        if (userDto.getPassword()==""||userDto.getPassword().equals("")) userN.setPassword(user.getPassword());
        else userN.setPassword(userDto.getPassword());
        userN.setEmail(userDto.getEmail());
        userN.setNickname(userDto.getNickname());
        userN.setAvatar(userDto.getAvatar());
        userN.setContent(userDto.getContent());
        if (userService.updateUser(userN)>0&&commentService.updateAvatar(userDto.getAvatar(),userId)>0){
            session.removeAttribute("adminUser");
            session.setAttribute("adminUser",userN);
            return Result.ok().data("ok","ok");
        }
        else {
            return Result.error();
        }

    }

}
