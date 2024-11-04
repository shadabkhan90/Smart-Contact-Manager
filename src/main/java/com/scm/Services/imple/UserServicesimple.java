package com.scm.Services.imple;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.Helpers.AppConstant;
import com.scm.Helpers.ResourceNotFoundException;
import com.scm.Repositories.UserRepo;
import com.scm.Services.UserService;
import com.scm.entity.User1;



@Service
public class UserServicesimple implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Override
    public User1 saveUser1(User1 user1) {
        String userId = UUID.randomUUID().toString();
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        //set the user role
        user1.getRoleList().add(AppConstant.ROLE_USER);
        


        user1.setUserId(userId);
        
        return userRepo.save(user1);

    }

    @Override
    public Optional<User1> updateUser1(User1 user1) {

        User1 user2 = userRepo.findById(user1.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        user2.setName(user1.getName());
        user2.setEmail(user1.getEmail());
        user2.setAbout(user1.getAbout());
        user2.setPassword(user1.getPassword());
        user2.setPhoneNumber(user1.getPhoneNumber());
        user2.setProfilePic(user1.getProfilePic());
        user2.setProvider(user1.getProvider());
        user2.setEnabled(user1.isEnabled());
        user2.setEmailVerified(user1.isEmailVerified());
        user2.setPhoneVerified(user1.isPhoneVerified());
        user2.setProviderUserId(user1.getProviderUserId());
        User1 save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public Optional<User1> deleteUser1(String userId) {
        User1 user2 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
        return null;
    }

    @Override
    public Optional<User1> isUserExists(String id) {
       User1 user2=userRepo.findById(id).orElse(null);
        return Optional.ofNullable(user2); 
    } 

    @Override
    public Optional<User1> getUserById(String id) {
        return userRepo.findById(id);
    }

       

    @Override
    public boolean isUserExistsByEmail(String email) {
        User1 user2=userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User1> getAllUsers() {
        return userRepo.findAll();
    }

}
