package com.example.demo.controller;


import com.example.demo.enums.UserRole;
import com.example.demo.security.CustomAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class MainController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("Password") String password,
                        HttpServletRequest request){

        if (userName.equals("Revaz") && password.equals("password")){
            HttpSession session = request.getSession();
            session.setAttribute("isAuthenticated", Boolean.TRUE);
            session.setAttribute("role", UserRole.ADMIN);
            session.setAttribute("username",userName);
        }
        return "success";
    }


    @GetMapping("/hello")
    public String hello(){
        SecurityContext context = SecurityContextHolder.getContext();
        CustomAuthentication authentication = (CustomAuthentication) context.getAuthentication();
/*       authentication.getPrincipal();
        authentication.isAuthenticated();
        authentication.getName();*/
        String name = authentication.getName();
        authentication.getFirstName();

        return name;
    }
}
