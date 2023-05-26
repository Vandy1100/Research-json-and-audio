package com.example.upload.game.json.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadAudio {
String uploadAudio(MultipartFile file) throws IOException;
}
