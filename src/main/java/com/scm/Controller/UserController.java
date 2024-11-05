package com.scm.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.Repositories.UserRepo;
import com.scm.entity.User1;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/dashboard")
    public String Userdashboard(Model model, Principal principal){
        String username = principal.getName();
        System.out.println(username);

        User1 user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User details: " + user.toString());
        model.addAttribute("user", user);
        return "user/dashboard";
    }
    @RequestMapping(value = "/profile")
    public String Userprofile(Model model, Principal principal){
        String username = principal.getName();
        User1 user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User details: " + user.toString());
        model.addAttribute("user", user);
        return "user/profile";
    }




}
