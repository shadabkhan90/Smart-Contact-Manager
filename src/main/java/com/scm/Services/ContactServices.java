package com.scm.Services;

import java.util.List;

import com.scm.entity.Contact;
import com.scm.entity.User1;



public interface ContactServices {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(String contactId);

    void deleteContact(String contactId );

   
    

    //search
    List<Contact> searchContact(String name, String email, String phone);

    
    

    List<Contact> getByUser(User1 user);





    

}
