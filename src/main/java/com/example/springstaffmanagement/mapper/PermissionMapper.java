package com.example.springstaffmanagement.mapper;

import com.example.springstaffmanagement.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    //根据中间表传来的pid信息查询所有权限信息
    public List<Permission> getallpermission(@Param("pid") Integer id);
}
