package com.natanight.petproject.service;

import com.natanight.petproject.models.User;
import com.natanight.petproject.models.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(
            String username,
            String password,
            String email
    ) {
        String hashPassword = passwordEncoder.encode(password);
        User user = new User(username, hashPassword, email);
        userRepository.save(user);
        log.info("User {} has been saved to database", username);
        return user;
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        String username = user.getUsername();
        userRepository.delete(user);
        log.info("User {} has been deleted from database", username);
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        log.info("User {} was read from database by id", user.getUsername());
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        log.info("User {} was read from database by username", user.getUsername());
        return user;
    }

    public User updateUser(
            Long id,
            String username,
            String email
    ) {
        User user = getUserById(id);
        if (username != null) {
            user.setUsername(username);
        }
        if (email != null) {
            user.setEmail(email);
        }
        userRepository.save(user);
        log.info("User {} was updated in database", user.getUsername());
        return user;
    }

    public List<User> getAllUsers() {
        log.info("List of all user has been gotten form database");
        return userRepository.findAll();
    }
}
