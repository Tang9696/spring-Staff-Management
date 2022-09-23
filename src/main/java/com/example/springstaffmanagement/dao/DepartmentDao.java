//package com.example.springstaffmanagement.dao;
//
//import com.example.springstaffmanagement.pojo.Department;
//import com.example.springstaffmanagement.service.DeptService;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//@Service("deptService")
//public class DepartmentDao implements DeptService {
//
//    //模拟数据库中的数据
//    private static Map<Integer, Department> departments = null;
//    static {
//        departments = new HashMap<Integer,Department>(); //创建一个部门表
//
//        departments.put(101,new Department(101,"Education Department"));
//        departments.put(102,new Department(102,"Market Department"));
//        departments.put(103,new Department(103,"Operational Department"));
//        departments.put(104,new Department(104,"Rear-Service Department"));
//        departments.put(105,new Department(105,"Finance Department"));
//
//    }
//
//    //获取所有信息
//    public Collection<Department> getDepartments(){
//        return departments.values();
//    }
//
//    //通过id得到部门
//    public Department getDepartmentById(Integer id){
//        return departments.get(id);
//    }
//}
