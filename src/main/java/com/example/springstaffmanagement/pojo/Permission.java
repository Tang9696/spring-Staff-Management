package com.example.springstaffmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable{
    private Integer id;
    private String name;
    private String comment;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createtime = formatter.format(date);
    String updatetime = formatter.format(date);

    private List<PermissionRole> permissionUsers;
}
