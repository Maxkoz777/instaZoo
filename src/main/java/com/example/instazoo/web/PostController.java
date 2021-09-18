package com.example.instazoo.web;

import com.example.instazoo.dto.PostDTO;
import com.example.instazoo.entity.Post;
import com.example.instazoo.facade.PostFacade;
import com.example.instazoo.service.PostService;
import com.example.instazoo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/post")
@CrossOrigin
public class PostController {

    private PostFacade postFacade;
    private PostService postService;
    private ResponseErrorValidation responseErrorValidation;

    @Autowired
    public PostController(PostFacade postFacade, PostService postService, ResponseErrorValidation responseErrorValidation) {
        this.postFacade = postFacade;
        this.postService = postService;
        this.responseErrorValidation = responseErrorValidation;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        Post post = postService.createPost(postDTO, principal);
        PostDTO createdPost = postFacade.potToPostDTO(post);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }


}
