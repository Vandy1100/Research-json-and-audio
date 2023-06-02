package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.MultipleGame;
import com.example.upload.game.json.model.request.MultipleGameRequest;
import com.example.upload.game.json.repository.MultipleGameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/multiple_game")
public class MultipleGameController {
    final
    MultipleGameRepository multipleGameRepository;

    public MultipleGameController(MultipleGameRepository multipleGameRepository) {
        this.multipleGameRepository = multipleGameRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertGame(@RequestBody MultipleGameRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode rootNode=objectMapper.createObjectNode();
        rootNode.put("sound",request.getSound());
        rootNode.put("question",request.getQuestion());
        rootNode.put("image",request.getImage());

//        ObjectNode answers = rootNode.putObject("answers");
        ArrayNode answersArray = rootNode.putArray("answers");
        List<String> word = request.getWord();
        List<Boolean> isCorrect=request.getIsCorrect();
        for(int i=0;i<word.size();i++){
            ObjectNode newObject=answersArray.addObject();
            newObject.put("word",word.get(i));
            newObject.put("isCorrect",isCorrect.get(i));
        }
        rootNode.put("titleGame",request.getTitleGame());
        rootNode.put("description",request.getDescription());
        String json=objectMapper.writeValueAsString(rootNode);
        multipleGameRepository.insertMultipleGame(json);
        System.out.println(json);
        return ResponseEntity.ok().body("success");
    }
    @GetMapping("/data")
    public ResponseEntity<List<MultipleGame>> getAllData(){
        List<MultipleGame> games=multipleGameRepository.getAllData();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    @GetMapping("/random")
    ResponseEntity<?> getByRandomGame(){
        List<MultipleGame> getAll=multipleGameRepository.getAllData();
        int index=new Random().nextInt(getAll.size());
        System.out.println(index);
        MultipleGame getRandom= getAll.get(index);
        System.out.println(getRandom);
        return ResponseEntity.ok(getRandom);
    }
}
