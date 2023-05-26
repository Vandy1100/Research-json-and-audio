package com.example.upload.game.json.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadAudioImp implements UploadAudio{
    Path locationAudio= Paths.get("src/main/resources/audio/");
    @Override
    public String uploadAudio(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(locationAudio + file.getOriginalFilename());
            Files.write(path, bytes);
        }

        return null;
    }
}
