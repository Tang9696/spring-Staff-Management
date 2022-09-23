package com.example.springstaffmanagement.serviceImpl;

import com.example.springstaffmanagement.mapper.DepartmentMapper;
import com.example.springstaffmanagement.pojo.Department;
import com.example.springstaffmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Repository
public class DeptServiceImpl implements DeptService {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments(Integer id) {
        return departmentMapper.getdept(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getdeptall();
    }

}
