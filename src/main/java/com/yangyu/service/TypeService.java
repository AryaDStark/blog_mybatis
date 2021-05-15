package com.yangyu.service;

import com.yangyu.po.Type;

import java.util.List;

public interface TypeService {

    List<Type> findAllTypes(Long userId);

    List<Type> findTopTypes(Long userId);

    void saveType(String name,Long userId);

    void deleteType(Long id);


    Type getByName(String name,Long userId);

    Type getById(Long id);

    void updateType(Type type);
 }
