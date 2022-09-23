package com.example.springstaffmanagement.mapper;

import com.example.springstaffmanagement.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    public List<Department> getdept(@Param("id")Integer id);

    public List<Department> getdeptall();
}
