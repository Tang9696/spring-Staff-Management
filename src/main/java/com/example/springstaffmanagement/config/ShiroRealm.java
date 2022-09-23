package com.example.springstaffmanagement.config;

import com.example.springstaffmanagement.pojo.*;
import com.example.springstaffmanagement.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    EmployeeService employeeService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //（1）拿到employee对象
        Employee primaryPrincipal = (Employee) principalCollection.getPrimaryPrincipal();
        //遍历用户的角色
        List<RoleUser> roleUsers = primaryPrincipal.getRoleUsers();
        List<String> permissionList = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        for(RoleUser user_role : roleUsers){
            //抽取出所有的角色列表
            List<Role> roleList = (List<Role>) user_role.getRole();
            for(Role role : roleList){
                //获取当前角色对应的权限
                List<PermissionRole> rolePermissions = role.getPermissionRoles();
                for(PermissionRole p : rolePermissions){
                    List<Permission> permissionList1 = (List<Permission>) p.getPermission();
                    for(Permission permission:permissionList1){
                                permissionList.add(permission.getName());
                                System.out.println("per:" + permission.getName());
                    }
                }
                //添加role
                roles.add(role.getName());
                System.out.println("role:"+role.getName());
            }
        }

        simpleAuthorizationInfo.addStringPermissions(permissionList);
        simpleAuthorizationInfo.addRoles(roles);

        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        //转换为我们controller里认识的token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //数据库取真实数据
        Employee employee = employeeService.getEmpByName(usernamePasswordToken.getUsername());
        if(employee == null){
            return null;
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",employee);
        Cookie cookie = new Cookie("rememberMe", null);
        if(cookie != null) {

        }

        //密码认证，shiro自动帮我们做
        System.out.println(usernamePasswordToken.getPassword());
        return new SimpleAuthenticationInfo(employee,employee.getPassword(), employee.getLastname());
    }
}
