package com.yangyu.web.adminUser;

import com.yangyu.dto.UserDto;
import com.yangyu.po.User;
import com.yangyu.service.UserService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {



    @Autowired
    UserService userService;


    // 登陆
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String username,@RequestParam String password,HttpSession session){
       //是否为管理员登陆
        User user = userService.checkUser(username,password);
        if (user!=null){
            session.setAttribute("adminUser",user);
            return Result.ok().data("ok",0).data("管理员对象信息",user);
        } else {
                  return Result.error().data("账号密码错误","请确认");
              }
    }

    //登出
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session){
        if (session.getAttribute("adminUser")!=null) {session.removeAttribute("adminUser");}
        return Result.ok().data("ok","over");
    }

    //普通用户注册
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody UserDto userDto){
        if (userService.getByUsername(userDto.getUsername())!=null){return Result.error().data("msg","用户名重复");}
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        if(userService.addUser(user)>0){
            return Result.ok().data("msg","注册成功");
        }else{
            return Result.error().data("msg","注册失败");
        }
    }

    //确认 是否登陆 的方法
    @GetMapping("/status")
    @ResponseBody
    public Result status(HttpSession session){
        if (session.getAttribute("adminUser")!=null){
            return Result.ok().data("messageOne","管理员已登陆").data("管理员",session.getAttribute("adminUser"));
        }
        else {
            return Result.error().data("messageThree","无人在线");
        }

    }

}
