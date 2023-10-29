package com.example.newschool.service;

import com.example.newschool.exception.AvatarNotFoundException;
import com.example.newschool.exception.StudentNotFoundException;
import com.example.newschool.model.Avatar;
import com.example.newschool.model.Student;
import com.example.newschool.repository.AvatarRepository;
import com.example.newschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AvatarService {
    public final StudentRepository studentRepository;
    public final AvatarRepository avatarRepository;
    public final String avatarsDir;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${avatar.dir}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
    }

    public void upload(Long studentId, MultipartFile file) throws IOException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());

        var path = saveFile(file, student);

        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);

    }

    private String saveFile(MultipartFile file, Student student) throws IOException {
        var dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }
        var dotIndex = file.getOriginalFilename().lastIndexOf(".");
        var ext = file.getOriginalFilename().substring(dotIndex + 1);
        var path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + ext;
        try (
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(path)
        ) {
            is.transferTo(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Transactional
    public Avatar find(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow(() -> new AvatarNotFoundException(studentId));
    }
}
