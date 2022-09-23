package com.example.springstaffmanagement.controller;

import com.example.springstaffmanagement.pojo.Department;
import com.example.springstaffmanagement.pojo.Employee;
import com.example.springstaffmanagement.service.DeptService;
import com.example.springstaffmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DeptService deptService;


    //查询所有员工，除了自己
    // Query all employees, except yourself
    @GetMapping("/emps")
    public String list(Model model){
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("emps",employees);
        return "table.html";
    }

    //查出所有部门信息，用于增加员工页面
    //Find out all department information for employees adding page
    @GetMapping("/emp")
    public String addpage(Model model){
        //查出所有部门信息
        List<Department> departments = deptService.getDepartments();
        for (Department list : departments ){
            System.out.println(list);
        }
        model.addAttribute("dept",departments);
        return "add.html";
    }

    //添加员工
    //add staff
    @PostMapping("/addemp")
    public String realadd(Employee employee){
        //接收前台参数,无需挨个拿，直接封装在了
        // employee中Receive foreground parameters, no need to take one by one, directly encapsulated in the employee
        employeeService.save(employee);
        //视图解析器自带的重定向方式,该方式只有被定义为@Controller的类可以使用
        return "redirect:/emps";
    }

    //通过id查询员工信息
    @GetMapping("/emp/{id}")
    public String updatePage(@PathVariable("id")Integer id, Model modle){
        Employee employee = employeeService.getEmployeeById(id);
        System.out.println(employee);
        modle.addAttribute("emp",employee);
        List<Department> departments = deptService.getDepartments();
        modle.addAttribute("dept",departments);
        return "update.html";
    }

    @PostMapping("/updateemp")
    public String realupdate(Employee employee){
        //接收前台参数,无需挨个拿，直接封装在了employee中
        employeeService.updateEmployee(employee);
        //视图解析器自带的重定向方式
        return "redirect:/emps";
    }

    @GetMapping("/empdelete/{id}")
    public String del(@PathVariable("id")Integer id){
        employeeService.deleteEmployee(id);
        //视图解析器自带的重定向方式
        return "redirect:/emps";
    }



}
