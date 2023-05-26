package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.response.AddressResponse;
import com.example.upload.game.json.repository.GameRepositoryTest;
import com.example.upload.game.json.repository.GetData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class GamesController {
    @Autowired
    private GameRepositoryTest myDataRepository;
    @Autowired
    GetData getData;
    @PostMapping
    public ResponseEntity<String> createData(@RequestBody String jsonData) throws JsonProcessingException {

        myDataRepository.insertData(jsonData);
        return ResponseEntity.ok().body("success");
    }
    @GetMapping("/all")
    public List<AddressResponse> getAllData(){
        List<AddressResponse> game=  getData.getAllData();
        return game;
    }


}
