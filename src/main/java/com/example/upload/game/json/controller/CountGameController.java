package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.CountGame;
import com.example.upload.game.json.model.request.CountGameRequest;
import com.example.upload.game.json.repository.CountGameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/count_game")
public class CountGameController {
    final
    CountGameRepository countGameRepository;

    public CountGameController(CountGameRepository countGameRepository) {
        this.countGameRepository = countGameRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertCountGame(@RequestBody CountGameRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode rootObject=objectMapper.createObjectNode();
        rootObject.put("sound",request.getSound());
        rootObject.put("question",request.getQuestion());
        rootObject.put("countGame",request.getCountNumber());
        rootObject.put("image",request.getImage());
        rootObject.put("title",request.getTitle());
        String json=objectMapper.writeValueAsString(rootObject);
        System.out.println(json);
        countGameRepository.insertCountGame(json);
        return ResponseEntity.ok().body("One row effected");
    }
    @GetMapping("/data")
    public ResponseEntity<List<CountGame>> getAllCountGameData(){
        List<CountGame> games=countGameRepository.getAllCountGame();
        return new ResponseEntity<List<CountGame>>(games, HttpStatus.OK);
    }
}
