package com.scm.Services.imple;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.Helpers.ResourceNotFoundException;
import com.scm.Repositories.ContactRepo;
import com.scm.Services.ContactServices;
import com.scm.entity.Contact;
import com.scm.entity.User1;
@Service
public class ContactServiceImpli implements ContactServices {

    @Autowired
    private ContactRepo contactRepo;
    @Override
    public Page<Contact> getByUser(User1 user1, int pageNumber, int pageSize,String sortBy, String direction) {
        Sort sort = direction.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return contactRepo.findByUser(user1, pageable);
    }

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);

        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        Contact contact2 = contactRepo.findById(contact.getId()).orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        contact2.setName(contact.getName());
        contact2.setEmail(contact.getEmail());
        contact2.setAddress(contact.getAddress());
        contact2.setDescription(contact.getDescription());
        contact2.setPhoneNumber(contact.getPhoneNumber());
        contact2.setFavorite(contact.isFavorite());
        return contactRepo.save(contact2);

      
    }

    @Override
    public List<Contact> getAllContacts() {

        
        return contactRepo.findAll();
    }

    @Override
    public Contact getContactById(String contactId) {
        return contactRepo.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
    }

    @Override
    public void deleteContact(String contactId) {
        Contact contact = getContactById(contactId);
        contactRepo.delete(contact);
    }

    @Override
    public Page<Contact> searchContacts(User1 user, String keyword, int pageNumber, int pageSize, String sortBy,
            String direction) {
                Sort sort = direction.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
                return contactRepo.findByUserAndNameContainingOrEmailContainingOrPhoneNumberContaining(user, keyword, keyword, keyword, pageable);
      
    }





   

    }



    







