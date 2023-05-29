package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.Game;
import com.example.upload.game.json.model.request.GameRequest;
import com.example.upload.game.json.repository.GameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
@CrossOrigin("http://localhost:3000")
public class GameController {
    @Autowired
    GameRepository gameRepository;
    @PostMapping("/add")
    public ResponseEntity<String> insertGame(@RequestBody GameRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootMapper = objectMapper.createObjectNode();
        rootMapper.put("titleGame",request.getTitleGame());
        rootMapper.put("description",request.getDescription());
        ObjectNode image=rootMapper.putObject("image");
        image.put("title",request.getTitle());
        image.put("score",request.getScore());
        image.put("url",request.getUrl());
        ArrayNode nameArray=rootMapper.putArray("name");
        for(String name1 : request.getName()){
            nameArray.add(name1);
        }
        String json = objectMapper.writeValueAsString(rootMapper);
        System.out.println(json);
        gameRepository.insertGame(json);
        return ResponseEntity.ok().body("One row effected.");
    }
    @GetMapping("/data")
    public ResponseEntity<List<Game>> getAllData(){
        List<Game> games=gameRepository.getAllData();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
    @GetMapping("/data/{title}")
    public ResponseEntity<Object> getDataById(@PathVariable("title") String title){
        try {
            System.out.println(title);
           Game game= gameRepository.getGameById(title);
            Map<String,Object> response=new HashMap<>();
            System.out.println(game);
            if(game!=null){
                response.put("payload",game);
                response.put("message","this is your data !!");
                response.put("status",HttpStatus.OK);
                return ResponseEntity.ok().body(response);
            }else {
                response.put("message","Not Found ! Please Try Choose Again!!");
                response.put("status",HttpStatus.BAD_REQUEST);
                return ResponseEntity.ok().body(response);
            }
        }catch (Exception ex){
            Map<String,Object> response=new HashMap<>();
            response.put("message","Error Exception !!");
            response.put("status",HttpStatus.EXPECTATION_FAILED);
            return ResponseEntity.ok().body(response);
        }
    }
}
