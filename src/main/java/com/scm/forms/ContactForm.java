package com.scm.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Please enter a valid phone number")
    private String phoneNumber;
    
    private String address;
    
    private MultipartFile profile;
    
    private String description;
    
    private boolean favorite;

    List<Contact> contacts;
}
