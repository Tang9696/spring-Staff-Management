package com.example.springstaffmanagement.mapper;

import com.example.springstaffmanagement.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    //根据中间表传来的rid信息查询所有role信息
    public List<Role> getallrole(@Param("rid") Integer id);
}
