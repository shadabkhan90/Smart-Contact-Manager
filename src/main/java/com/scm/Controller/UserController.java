package com.scm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "user/dashboard";
    }
    @RequestMapping("/profile")
    public String profile(){
        return "user/profile";
    }




}
