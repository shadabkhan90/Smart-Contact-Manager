package com.scm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.Helpers.message;
import com.scm.Helpers.messagetype;
import com.scm.Services.UserService;
import com.scm.entity.User1;
import com.scm.forms.UserForm;

import jakarta.servlet.http.HttpSession;

@Controller // Add base path for all endpoints
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home1") // Changed from /home1 for consistency
    public String home() {
        return "home1"; // Template name should match URL for consistency
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage() {
        return "services";
    }

    @RequestMapping("/base")
    public String basePage() {
        return "base";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        UserForm userfrom = new UserForm();

        model.addAttribute("userForm", userfrom);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        // fetch data from user
        System.out.println(userForm);
        // save data in database

        User1 user1 = new User1();
        user1.setName(userForm.getName());
        user1.setPassword(userForm.getPassword());
        user1.setPhoneNumber(userForm.getPhoneNumber());
        user1.setEmail(userForm.getEmail());
        user1.setAbout(userForm.getAbout());

        User1 savedUser = userService.saveUser1(user1);

        System.out.println("User saved successfully: " + savedUser);

        message msg = message.builder()
                .content("Registration Successful")
                .type(messagetype.GREEN)
                .build();

        session.setAttribute("message", msg);

        //
        return "redirect:/register";
    }

}
