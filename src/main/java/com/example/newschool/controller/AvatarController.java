package com.example.newschool.controller;

import com.example.newschool.model.Avatar;
import com.example.newschool.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    public final AvatarService avatarService;


    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("{studentId}")
    public void upload(@PathVariable Long studentId, MultipartFile file) throws IOException {
        avatarService.upload(studentId, file);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<byte[]> find(@PathVariable Long studentId) throws IOException {
        Avatar avatar = avatarService.find(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping("/file/{studentId}")
    public void findFile(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.find(studentId);
        File file = new File(avatar.getFilePath());
        if(!file.exists()) {
            response.setStatus(500);
            return;
        }
        try (InputStream is = new FileInputStream(avatar.getFilePath());
             OutputStream os = response.getOutputStream();
        ) {
            is.transferTo(os);
        }

        response.setContentType(avatar.getMediaType());
        response.setContentLength(avatar.getFileSize().intValue());

    }
}
