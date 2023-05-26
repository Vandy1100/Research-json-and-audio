package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.Game;
import com.example.upload.game.json.model.request.AddressRequest;
import com.example.upload.game.json.model.response.AddressResponse;
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
    GetData getData;
    @PostMapping
    public ResponseEntity<String> createData(@RequestBody AddressRequest request) throws JsonProcessingException {
        String json = "{\"country\":\"" +request.getCountry()+"\", \"province\": {\"city\":\"" +request.getCity()+"\", \"village\":\"" +request.getVillage()+"\"}}";
        System.out.println(json);
        getData.insertAddress(json);
        return ResponseEntity.ok().body("ok");
    }
    @GetMapping("/all")
    public List<AddressResponse> getAllData(){
        List<AddressResponse> game=  getData.getAllData();
        return game;
    }


}
