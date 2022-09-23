package com.example.springstaffmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//员工
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private Integer id;
    private Integer depId;
    private String lastname;
    private String password;
    private String email;
    private Integer gender; //0:女，1：男
    //多对一关系，要在多的一方创建一的实体
    private Department department;
    private String birth;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createtime = formatter.format(date);
    String updatetime = formatter.format(date);

    private List<RoleUser> roleUsers;
}
