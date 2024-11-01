package com.scm.Helpers;

import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component("sessionHelper")
public class SessionHelper {
    public String removeMessage() {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
