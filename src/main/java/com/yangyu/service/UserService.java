package com.yangyu.service;

import com.yangyu.po.User;

public interface UserService {
    User getById(Long id);

    int addUser(User user);

    User checkUser(String username,String password);

    int updateUser(User user);
}
