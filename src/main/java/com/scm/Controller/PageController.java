package com.scm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // Add base path for all endpoints
public class PageController {

    @RequestMapping("/home1")   // Changed from /home1 for consistency
    public String home() {
        return "home";     // Template name should match URL for consistency
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
}
