//package com.example.springstaffmanagement.dao;
//
//import com.example.springstaffmanagement.pojo.Department;
//import com.example.springstaffmanagement.pojo.Employee;
//import com.example.springstaffmanagement.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//@Service("employeeService")
//public class EmployeeDao implements EmployeeService {
//    private static Map<Integer, Employee> employees = null;
//
//
//    static {
//        employees = new HashMap<Integer,Employee>();
//
////        employees.put(1001,new Employee(1001,"Tom","234@qq.com",1,new Department(101,"Education Department")));
////        employees.put(1002,new Employee(1002,"Merry","879@qq.com",0,new Department(102,"Market Department")));
////        employees.put(1003,new Employee(1003,"Mike","098@qq.com",1,new Department(103,"Operational Department")));
////        employees.put(1004,new Employee(1004,"Jerry","777@qq.com",1,new Department(103,"Operational Department")));
////        employees.put(1005,new Employee(1005,"Linda","okl@qq.com",0,new Department(105,"Finance Department")));
//
//    }
//
//    //增加员工，主键自增
//    private static Integer initId = 1006;
//    public void save(Employee employee){
//        if(employee.getId() == null){
//            employee.setId(initId++);
//        }
//        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
//        employees.put(employee.getId(),employee);
//    }
//
//    //查询全部员工
//    public Collection<Employee> getAll(){
//        System.out.println(employees.values());
//        return employees.values();
//    }
//
//    //通过id查询员工
//    public Employee getEmployeeById(Integer id){
//        return employees.get(id);
//    }
//
//    //删除员工
//    public void delete(Integer id){
//        employees.remove(id);
//    }
//
//
//}
