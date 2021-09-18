package com.example.instazoo.web;

import com.example.instazoo.entity.ImageModel;
import com.example.instazoo.payload.response.MessageResponse;
import com.example.instazoo.service.ImageUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping
public class ImageUploadController {

    private ImageUploadService imageUploadService;

    @Autowired
    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file")MultipartFile file,
                                                             Principal principal) throws IOException {
        imageUploadService.uploadImageToUser(file, principal);
        return ResponseEntity.ok(new MessageResponse("Image uploaded successfully"));
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(@PathVariable("postId") String postId,
                                                     @RequestParam("file") MultipartFile file,
                                                     Principal principal) throws IOException {
        imageUploadService.uploadImageToPost(file, principal, Long.valueOf(postId));
        return ResponseEntity.ok(new MessageResponse("Image uploaded successfully"));
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal) {
        ImageModel imageModel = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(imageModel, HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    public ResponseEntity<ImageModel> getImageForUser(@PathVariable("postId") String postId) {
        ImageModel imageModel = imageUploadService.getImageToPost(Long.valueOf(postId));
        return new ResponseEntity<>(imageModel, HttpStatus.OK);
    }


}
