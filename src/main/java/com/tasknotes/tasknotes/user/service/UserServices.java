package com.tasknotes.tasknotes.user.service;

import java.util.List;
import java.util.Optional;

import com.tasknotes.tasknotes.user.repository.User;

public interface UserServices {
    
    List<User> getAllUsers();

    void addUser(User user);

    Optional<User> findByUsername(String username);

}
