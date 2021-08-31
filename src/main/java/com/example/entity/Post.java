package com.example.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {

    private Long id;
    private String Title;
    private String caption;
    private String Location;
    private Integer likes;

    private Set<String> likedUsers = new HashSet<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createdDate;

    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

}
