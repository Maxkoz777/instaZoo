package com.example.instazoo.service;

import com.example.instazoo.entity.User;
import com.example.instazoo.entity.enums.ERole;
import com.example.instazoo.exceptions.UserExistException;
import com.example.instazoo.payload.request.SignupRequest;
import com.example.instazoo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            log.info("Saving User {}", user.getUsername());
            return userRepository.save(user);
        }
        catch (Exception exception) {
            log.error("Error during registration. {}", exception.getMessage());
            throw new UserExistException("The user " + " already exist. Please, check the credentials");
        }
    }

}
