package com.yangyu.service;

import com.yangyu.po.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    User getByUsername(String username);

    List<User> getByName(String keywords);

    int addUser(User user);

    User checkUser(String username,String password);

    int updateUser(User user);
}
