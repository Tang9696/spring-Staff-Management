package com.example.springstaffmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRole implements Serializable {
    private Integer id;
    private Integer pid;
    private Integer rid;


    private List<Permission> permission;
    private Role role;
}
