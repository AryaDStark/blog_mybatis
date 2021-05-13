package com.yangyu.mapper;

import com.yangyu.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    User getById(Long id);

    User getByUsername(String username);

    List<User> getByName(String keywords);

    int addUser(User user);

    User checkUser(@Param("u") String username,@Param("p") String password);

    int updateUser(User user);
}
