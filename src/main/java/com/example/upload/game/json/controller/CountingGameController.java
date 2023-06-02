package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.CountingGame;
import com.example.upload.game.json.model.request.CountingGameRequest;
import com.example.upload.game.json.repository.CountingGameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v2/game")
@CrossOrigin("http://localhost:3000/")
public class CountingGameController {
    private final String CLIENT_PATH="http://localhost:8080/images/";
    private final String serviceLocation = "src\\main\\resources\\images";
    private final CountingGameRepository countingGameRepository;

    public CountingGameController(CountingGameRepository countingGameRepository) {
        this.countingGameRepository = countingGameRepository;
    }

    @PostMapping
    public String insertCountingGame(@RequestBody CountingGameRequest request) throws JsonProcessingException {
       ObjectMapper objectMapper=new ObjectMapper();
       ObjectNode rootObject=objectMapper.createObjectNode();
       rootObject.put("code",request.getCode());
       ObjectNode typeGame=rootObject.putObject("type");
       typeGame.put("id",request.getTypeId());
       typeGame.put("name",request.getTypeGame());
       rootObject.put("level",request.getLevel());

       ObjectNode instruction=rootObject.putObject("instruction");
       instruction.put("text",request.getInstructionText());
       instruction.put("sound",request.getInstructionSound());
       instruction.put("image",request.getInstructionImage());
       instruction.put("video",request.getInstructionVideo());

       ObjectNode question =rootObject.putObject("question");
       question.put("text",request.getInstructionText());
       question.put("sound", request.getQuestionSound());
       question.put("image",request.getQuestionImage());
       question.put("video",request.getQuestionVideo());
        question.put("value",request.getQuestionValue());
       ArrayNode answerCount=rootObject.putArray("answer");
       for(Integer count :request.getAnswerCount()){
           answerCount.add(count);
       }
       String json= objectMapper.writeValueAsString(rootObject);
       System.out.println(json);
       countingGameRepository.insertCountingGame(json);
       return "successfully";
   }
   @GetMapping
    public ResponseEntity<?> getDataCountingGame() throws JsonProcessingException {
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

       ObjectNode rootObject=mapper.createObjectNode();
       rootObject.put("imageGenerate",CLIENT_PATH+selectedFile);
       rootObject.putPOJO("data",countingGameRepository.getAllCountingGame());
       String json=mapper.writeValueAsString(rootObject);
       return ResponseEntity.ok(json);
   }
    @GetMapping("/data")
    public ResponseEntity<?> getDataCounting() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<CountingGame> list =countingGameRepository.getAllCountingGame();
        File directory = new File(serviceLocation);
        File[] files = directory.listFiles(File::isFile);

        // Check if files array is null or empty
        if (files == null || files.length == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // Generate random index from 0 to files.length - 1
        int index = new Random().nextInt(files.length);
        int size = new Random().nextInt(list.size());
        System.out.println(index);
        // Get the name of the selected file
        String selectedFile = files[index].getName();
        CountingGame countingGame=countingGameRepository.getAllCountingGame().get(size);
        ObjectNode rootObject=mapper.createObjectNode();
        rootObject.put("imageGenerate",CLIENT_PATH+selectedFile);
        rootObject.putPOJO("data",countingGame);
        String json=mapper.writeValueAsString(rootObject);
        return ResponseEntity.ok(json);
    }
}
