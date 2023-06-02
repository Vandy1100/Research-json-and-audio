package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.MultipleGame;
import com.example.upload.game.json.repository.MultipleGameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@RestController
@AllArgsConstructor
public class GenerateImage {
    private final MultipleGameRepository multipleGameRepository;
    private final String CLIENT_PATH="http://localhost:8080/images/";
    private final String serviceLocation = "src\\main\\resources\\images";
//    @GetMapping("/ok")
//    public ResponseEntity<?> generateImage() {
//        File directory = new File(serviceLocation);
//        File[] files = directory.listFiles(File::isFile); // Use method reference to filter files
//        Random random = new Random();
//        int index = random.nextInt(files.length);
//        System.out.println(files[index]);
//        Map<String,String> images=new HashMap<>();
//        images.put("images", String.valueOf(files[index]));
//        System.out.println(images);
//        return new ResponseEntity<>(images, HttpStatus.OK);
//    }
//        @GetMapping("/ok")
//    public ResponseEntity<File[]> generateImage() {
//        File directory = new File(serviceLocation);
//        File[] files = directory.listFiles();
//        List<String> fileNames = new ArrayList<>();
//        Random random =new Random();
//        int ren = random.nextInt(files.length-1);
//        System.out.println(files[ren]);
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    fileNames.add(file.getName());
//                }
//            }
//        }
//        String[] getData= new String[]{fileNames.stream().findFirst().orElse(null)};
//            System.out.println(getData[ren]);
//        return null;
//    }
//@GetMapping("/ok")
//public ResponseEntity<?> generateImage() throws IOException {
//    File directory = new File(serviceLocation);
//    File[] files = directory.listFiles(File::isFile);
//    Random random = new Random();
//    int index = random.nextInt(files.length);
//    String selectedFile = files[index].getName();
//
//    Map<String,String> images=new HashMap<>();
//    images.put("images",CLIENT_PATH+selectedFile);
//    images.put("json", multipleGameRepository.getAllData());
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(images);
//    System.out.println(json);
//    return new ResponseEntity<>(images, HttpStatus.OK);
//}
//@GetMapping("/ok")
//public ResponseEntity<?> generateImage() throws IOException {
//    ObjectMapper mapper = new ObjectMapper();
//    File directory = new File(serviceLocation);
//    File[] files = directory.listFiles(File::isFile);
//    Random random = new Random();
//    int index = random.nextInt(files.length);
//    String selectedFile = files[index].getName();
//    ObjectNode objectRoot=mapper.createObjectNode();
//    Map<String,Object> response=new HashMap<>();
//    response.put("image", CLIENT_PATH + selectedFile);
//    response.put("data", multipleGameRepository.getAllData());
//
//    String json = mapper.writeValueAsString(response);
//    return new ResponseEntity<>(json, HttpStatus.OK);
//}
@GetMapping("/ok")
public ResponseEntity<String> generateImage() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File directory = new File(serviceLocation);
    File[] files = directory.listFiles(File::isFile);

    // Check if files array is null or empty
    if (files == null || files.length == 0) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Generate random index from 0 to files.length - 1
    int index = new Random().nextInt(files.length);
    System.out.println(index);
    // Get the name of the selected file
    String selectedFile = files[index].getName();

    // Create a JSON object to hold the response data
    ObjectNode response = mapper.createObjectNode();
    response.put("image", CLIENT_PATH + selectedFile);
    response.putPOJO("data", multipleGameRepository.getAllData());

    // Convert the JSON object to a string
    String json = mapper.writeValueAsString(response);

    // Return the response with HTTP status code 200 (OK)
    return ResponseEntity.ok(json);
}
}
