package com.scm.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.scm.entity.Contact;
import com.scm.entity.User1;



public interface ContactServices {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(String contactId);

    void deleteContact(String contactId );

   
    

    //search
    Page<Contact> searchContacts(User1 user, String keyword, int pageNumber, int pageSize, String sortBy, String direction);


    
    

    Page<Contact> getByUser(User1 user1, int pageNumber, int pageSize,String sortBy, String direction);





    

}
