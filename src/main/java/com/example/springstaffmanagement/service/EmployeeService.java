package com.example.springstaffmanagement.service;
import com.example.springstaffmanagement.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface EmployeeService {


    //查询所有员工
    List<Employee> getAll();

    //查询管理员密码
    public String getAdminPassword(String username);

    //添加员工
    public void save(Employee employee);

    //根据id查找员工
    public Employee getEmployeeById(Integer id);

    //修改员工
    void updateEmployee(Employee employee);

    //删除员工
    void deleteEmployee(Integer id);

    //shiro认证，根据用户名查找用户信息
    public Employee getEmpByName(String username);

}
