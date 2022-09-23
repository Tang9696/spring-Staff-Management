package com.example.springstaffmanagement.config;

import com.example.springstaffmanagement.pojo.Employee;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;

import static java.lang.System.out;

public class LoginHandlerIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Employee employee = (Employee) request.getSession().getAttribute("loginUser");

        //登陆后判断用户session
                if (employee == null) {
                    request.setAttribute("msg", "No Permissions");
                    request.getRequestDispatcher("/signin.html").forward(request, response);
                    out.println("ssssssss");
                    return false;
                } else {
                    return true;
                }
        //获取客户端保存的最后访问时间
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; cookies != null && i < cookies.length; i++) {
//            if (!cookies[i].getName().equals("null")&&employee == null) {//判断当前Cookie中的name是否是想要的cookie
//                String l = cookies[i].getValue();//如果是想要的Cookie，则把Cookie中的value取出
//                out.println(l);
//                return true;
//            } else {
//                //登陆后判断用户session
//                if (employee == null&&cookies[i].getName().equals("null")) {
//                    request.setAttribute("msg", "No Permissions");
//                    request.getRequestDispatcher("/signin.html").forward(request, response);
//                    out.println("ssssssss");
//                    return false;
//                } else {
//                    return true;
//                }
//
//            }
//        }
//        out.println("ok");
//        return true;

}
}
