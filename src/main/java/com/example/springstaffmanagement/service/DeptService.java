package com.example.springstaffmanagement.service;

import com.example.springstaffmanagement.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {

    //根据员工的部门id 查询所有部门信息
    List<Department> getDepartments(Integer id);

    //查询所有员工信息
    List<Department> getDepartments();
}
