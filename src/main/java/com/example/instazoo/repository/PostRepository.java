package com.example.instazoo.repository;

import com.example.instazoo.entity.Post;
import com.example.instazoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, User user);

}
