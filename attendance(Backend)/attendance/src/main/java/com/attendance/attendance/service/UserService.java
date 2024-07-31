package com.attendance.attendance.service;

import com.attendance.attendance.model.User;
import com.attendance.attendance.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    public User registerUser(String username, String password, String email, String phone) {

        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User(username, password, email, phone);
        return userRepository.save(newUser);
    }
    public boolean loginUser(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Transactional
//    public boolean deleteUserByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            userRepository.delete(user);
//            return true;
//        }
//        return false;
//    }
}
