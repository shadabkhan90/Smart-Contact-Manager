package com.scm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/dashboard")
    public String Userdashboard(){
        return "user/dashboard";
    }
    @RequestMapping(value = "/profile")
    public String Userprofile(){
        return "user/profile";
    }




}
