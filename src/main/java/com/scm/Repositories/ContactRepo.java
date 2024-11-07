package com.scm.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.entity.Contact;
import com.scm.entity.User1;

public interface ContactRepo extends JpaRepository<Contact, String> {

    Page<Contact> findByUser(User1 user, Pageable pageable);

    Page<Contact> findByUserAndNameContainingOrEmailContainingOrPhoneNumberContaining(
    User1 user, String name, String email, String phone, Pageable pageable);

}
