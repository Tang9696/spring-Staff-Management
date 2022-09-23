package com.example.springstaffmanagement.mapper;

import com.example.springstaffmanagement.pojo.PermissionRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionRoleMapper {

    //根据role表的id字段查询中间表的pid--中间表
    public List<PermissionRole> getpermissionid(@Param("rid") Integer id);
}
