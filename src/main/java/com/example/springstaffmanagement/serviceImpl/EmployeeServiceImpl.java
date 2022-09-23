package com.example.springstaffmanagement.serviceImpl;

import com.example.springstaffmanagement.mapper.EmployeeMapper;
import com.example.springstaffmanagement.pojo.Employee;
import com.example.springstaffmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    //查询所有员工
    @Override
    public List<Employee> getAll() {
        return employeeMapper.getall();
    }

    //查询管理员密码
    @Override
    public String getAdminPassword(String username) {
        return employeeMapper.getAdminPassword(username);
    }

    //添加员工
    @Override
    public void save(Employee employee) {
        employeeMapper.save(employee);
    }

    //根据id查找员工
    @Override
    public Employee getEmployeeById(Integer id) {

        return employeeMapper.getEmployeeById(id);
    }

    //删除员工
    @Override
    public void deleteEmployee(Integer id) {
       employeeMapper.deleteEmployee(id);
    }

    //shiro认证，根据用户名查找用户信息
    @Override
    public Employee getEmpByName(String username) {
        return employeeMapper.getEmpByName(username);
    }

    //修改员工
    @Override
    public void updateEmployee(Employee employee){
       employeeMapper.updateEmployee(employee);
    }
}
