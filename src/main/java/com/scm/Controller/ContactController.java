package com.scm.Controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import org.springframework.data.domain.Page;
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
            @RequestParam(value = "profile", required = false) MultipartFile file,
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

        contactServices.save(contact);
        message msg = message.builder()
                .content("Contact Added Successfully")
                .type(messagetype.GREEN)
                .build();

        session.setAttribute("message", msg);
        return "redirect:/user/contact/add";
    }

    @RequestMapping()
    public String viewContact(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Principal principal) {
        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        model.addAttribute("user", user);
        // Get all contacts of the user
        Page<Contact> pagecontact = contactServices.getByUser(user, pageNumber, pageSize, sortBy, direction);
        model.addAttribute("pagecontact", pagecontact);
        return "user/view_contacts";
    }

    @RequestMapping("/search")
    public String searchContact(

            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Principal principal) {

        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        model.addAttribute("user", user);

        if (keyword.isEmpty()) {
            return "redirect:/user/contact";
        }

        // Search across all fields with the keyword
        Page<Contact> pagecontact = contactServices.searchContacts(user, keyword, pageNumber, pageSize, sortBy,
                direction);
        model.addAttribute("pagecontact", pagecontact);
        model.addAttribute("keyword", keyword); // Add this to preserve the search term

        return "user/search_contacts";
    }

    @RequestMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") String id, Principal principal, HttpSession session) {
        contactServices.deleteContact(id);
        return "redirect:/user/contact";
    }

    @RequestMapping("/edit/{id}")
    public String editContact(@PathVariable("id") String id, Model model, Principal principal) {
        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Contact contact = contactServices.getContactById(id);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setFavorite(contact.isFavorite());

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contact", contact);
        model.addAttribute("user", user);
        return "user/edit_contacts";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("id") String id, @ModelAttribute ContactForm contactForm,
            Principal principal, HttpSession session) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setFavorite(contactForm.isFavorite());
        contactServices.update(contact);

        message msg = message.builder()
                .content("Contact Updated Successfully")
                .type(messagetype.GREEN)
                .build();

        session.setAttribute("message", msg);
        return "redirect:/user/contact/edit/" + id;
    }

    @RequestMapping("/details/{id}")
    public String contactDetails(@PathVariable("id") String id, Model model, Principal principal) {
        String userName = principal.getName();
        User1 user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Contact contact = contactServices.getContactById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("user", user);
        return "user/Contact_Details";
    }
}
