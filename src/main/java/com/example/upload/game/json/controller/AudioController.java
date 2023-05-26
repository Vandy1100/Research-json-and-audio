package com.example.upload.game.json.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AudioController {
    Path locationAudio= Paths.get("src/main/resources/audio");
    @PostMapping("/audio")
    public ResponseEntity<String> uploadAudio(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(locationAudio + "/" + file.getOriginalFilename());
                System.out.println(path);


//                if (!file.getContentType().startsWith("/audio")) {
//                    return ResponseEntity.badRequest().body("Only audio files are allowed.");
//                }

                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                if (!extension.equals("mp3") && !extension.equals("wav") && !extension.equals("ogg")) {
                    return ResponseEntity.badRequest().body("Only MP3, WAV, and OGG audio files are allowed.");
                } else {
                    // Save the file to the server
                    // ...
                    Files.write(path, bytes);
                    return ResponseEntity.ok("File uploaded successfully.");
                }
            }else {
                return ResponseEntity.ok("Failed");
            }
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }

    }
}
