package com.scm.Controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.scm.Helpers.ResourceNotFoundException;
import com.scm.Helpers.message;
import com.scm.Helpers.messagetype;
import com.scm.Repositories.UserRepo;
import com.scm.Services.ContactServices;

import com.scm.entity.Contact;
import com.scm.entity.User1;
import com.scm.forms.ContactForm;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ContactServices contactServices;

    @GetMapping("/add")
    public String addContactView(Model model, Principal principal) {
        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("contactForm", new ContactForm());

        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm,
            @RequestParam("profile") MultipartFile file,
            Principal principal, HttpSession session) {

        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setFavorite(contactForm.isFavorite());
        contact.setUser(user);

        // Handle file upload
        if (!file.isEmpty()) {
            try {
                // Add your file handling logic here
                // For example, save to filesystem or cloud storage
                // contact.setProfileImage(savedFilePath);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle error appropriately
            }
        }

        contactServices.save(contact);
        message msg = message.builder()
                .content("Contact Added Successfully")
                .type(messagetype.GREEN)
                .build();

        session.setAttribute("message", msg);
        return "redirect:/user/contact/add";
    }

}
