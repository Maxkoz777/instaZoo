package com.example.instazoo.repository;

import com.example.instazoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);

    Optional<User> finsUserByEmail(String email);

    Optional<User> findUserById(Long id);

}
