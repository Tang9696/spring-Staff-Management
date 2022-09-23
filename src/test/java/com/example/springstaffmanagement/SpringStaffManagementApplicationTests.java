package com.example.springstaffmanagement;

import com.example.springstaffmanagement.pojo.Employee;
import com.example.springstaffmanagement.service.DeptService;
import com.example.springstaffmanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringStaffManagementApplicationTests {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DeptService deptService;

    @Test
    void contextLoads() {
        List<Employee> list = employeeService.getAll();
        for(Employee employee : list){
            System.out.println(employee);
        }
    }

}
