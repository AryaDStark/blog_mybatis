package com.yangyu.web.adminUser;

import com.mysql.cj.util.DnsSrv;
import com.yangyu.po.Type;
import com.yangyu.po.User;
import com.yangyu.service.BlogService;
import com.yangyu.service.TypeService;
import com.yangyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    //分页展示
    @GetMapping("/typeControl/{pageNum}")
    @ResponseBody
    public Result showAllTypes(@PathVariable int pageNum,Long userId){
        if(pageNum==-1){  pageNum=0;  }
        return Result.ok().data("types",typeService.findAllTypes(pageNum,userId));
    }

    //增加
    @GetMapping("/addType")
    @ResponseBody
    public Result addType(String name, HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
        typeService.saveType(name,userId);
        return  Result.ok().data("message","成功");
    }

    //删除
    @GetMapping("/deleteType/{id}")
    @ResponseBody
    public Result deleteType(@PathVariable Long id){
        blogService.deleteType(id);
        typeService.deleteType(id);
        return  Result.ok().data("message","成功");
    }

    //填充 未作修改 的页面
    @GetMapping("/updateType/{id}")
    @ResponseBody
    public Result showThisType(@PathVariable Long id){
        return  Result.ok().data("type",typeService.getById(id));
    }


    //提交修改后的 type
    @GetMapping("/updateType")
    @ResponseBody
    public Result updateType(@RequestBody Type type, HttpSession session){
        User user = (User)session.getAttribute("adminUser");
        Long userId = user.getId();
         if (typeService.getByName(type.getName(),userId)==null){
             typeService.updateType(type);
             return Result.ok().data("message","over");
         }
         else{ return Result.error().data("message","名儿重了 换个");}
    }


}
