package com.scm.Services;

import java.util.List;
import java.util.Optional;

import com.scm.entity.User1;

public interface UserService {

    User1 saveUser1(User1 user1);

    Optional<User1> updateUser1(User1 user1);

    Optional<User1> deleteUser1(String userId);

    Optional<User1> isUserExists(String id);

    Optional<User1> getUserById(String id);

    boolean isUserExistsByEmail(String email);

    List<User1> getAllUsers();

}
