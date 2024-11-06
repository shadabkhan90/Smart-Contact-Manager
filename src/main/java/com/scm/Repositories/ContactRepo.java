package com.scm.Repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.entity.Contact;
import com.scm.entity.User1;

public interface ContactRepo extends JpaRepository<Contact, String> {



    

    List<Contact> findByUser(User1 userID);

}
