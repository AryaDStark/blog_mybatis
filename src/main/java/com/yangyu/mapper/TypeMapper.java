package com.yangyu.mapper;

import com.yangyu.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface TypeMapper {

    List<Type> findAllTypes(@Param("page") Integer pageNum,@Param("userId")Long userId);
    List<Type> findTopTypes(Long userId);
    void       saveType(@Param("name") String name,@Param("userId")Long userId);
    Type        getById(@Param("id")Long id);
    Type        getByName(@Param("name")String name,@Param("userId")Long userId);
    void       deleteType(@Param("id") Long id);
    void        updateType(Type type);

}
