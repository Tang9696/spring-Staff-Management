package com.example.springstaffmanagement.controller;

import com.example.springstaffmanagement.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    EmployeeService employeeService;



    @RequestMapping("/user/login")
    //@ResponseBody
    public String login(String username,String password,boolean rememberMe,Model model, HttpSession session){
            //获取当前用户
            Subject subject = SecurityUtils.getSubject();
            //查询登录用户的ID
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            //设置记住我
            token.setRememberMe(rememberMe);
            System.out.println("是否记住我" + rememberMe);
            //表示用户是通过记住我登录的，此时可能并不是真正的你（如你的朋友使用你的电脑，或者你的cookie 被窃取）在访问的。
            //token.isRememberMe();

            //执行登录方法，没有异常就可以
            try {
                subject.login(token);
                return "redirect:/index";
            } catch (IncorrectCredentialsException ice) {
                //password didn't match, try again?
                model.addAttribute("msg", "Password Wrong");
                return "signin";
            } catch (AuthenticationException e) {//认证异常
                model.addAttribute("msg", "Authentication Wrong");
                return "signin";
            }

    }

    @GetMapping("/index")
    public String index(){
        return "/index";
    }
    @GetMapping ("/login")
    public String loginhtml(){
        return "/signin";
    }

    @RequestMapping ("/logout")
    public String logout(HttpSession session, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        session.removeAttribute("loginUser");
        if (subject.isAuthenticated()) {
            subject.logout();
            Cookie cookie = new Cookie("rememberMe", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/user/login";
    }

    @GetMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "page of unauthorized!";
    }


}
