package com.yangyu.service.Impl;

import com.yangyu.mapper.TypeMapper;
import com.yangyu.po.Type;
import com.yangyu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
   @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> findTopTypes(Long userId) {
        return typeMapper.findTopTypes(userId);
    }

    @Override
    public List<Type> findAllTypes(Long userId) {
        return typeMapper.findAllTypes(userId);
    }

    @Override
    public void saveType(String name,Long userId) {
        typeMapper.saveType(name,userId);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public Type getById(Long id) {
        return typeMapper.getById(id);
    }

    @Override
    public Type getByName(String name,Long userId) {
        return typeMapper.getByName(name,userId);
    }

    @Override
    public void updateType(Type type) {
        typeMapper.updateType(type);
    }
}
