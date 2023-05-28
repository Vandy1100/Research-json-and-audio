package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.JsonData;
import com.example.upload.game.json.model.request.AddressRequest;
import com.example.upload.game.json.model.response.Address;
import com.example.upload.game.json.repository.GetData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class GamesController {
    @Autowired
    GetData getData;
//    @PostMapping
//    public ResponseEntity<String> createData(@RequestBody AddressRequest request) throws JsonProcessingException {
//        String json = "{\"country\":\"" +request.getCountry().toString()+"\", \"province\": {\"city\":\"" +request.getCity()+"\", \"village\":\"" +request.getVillage()+"\"}}";
//        System.out.println(json);
//        getData.insertAddress(json);
//        return ResponseEntity.ok().body("ok");
//    }
@PostMapping
public ResponseEntity<String> createData(@RequestBody AddressRequest request) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode rootNode = objectMapper.createObjectNode();

    ArrayNode countryNode = rootNode.putArray("country");
    for (String country : request.getCountry()) {
        countryNode.add(country);
    }

    ObjectNode provinceNode = rootNode.putObject("province");
    ArrayNode cityNode=provinceNode.putArray("city");
    for(String city : request.getCity()){
        cityNode.add(city);
    }
//    provinceNode.put("city", request.getCity());
    provinceNode.put("village", request.getVillage());

    String json = objectMapper.writeValueAsString(rootNode);
    System.out.println(json);

    getData.insertAddress(json);
    return ResponseEntity.ok().body("ok");
}
    @GetMapping("/all")
    public List<JsonData> getAllData(){
        List<JsonData> jsonData=  getData.getAllJson();
        return jsonData;
    }


}
