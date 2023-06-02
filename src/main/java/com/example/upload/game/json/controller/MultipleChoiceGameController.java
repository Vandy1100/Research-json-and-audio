package com.example.upload.game.json.controller;

import com.example.upload.game.json.model.MultipleGame;
import com.example.upload.game.json.model.request.MultipleChoiceGameRequest;
import com.example.upload.game.json.repository.MultipleGameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vi/game")
@CrossOrigin("http://localhost:3000/")
public class MultipleChoiceGameController {
   private final MultipleGameRepository multipleGameRepository;
    @PostMapping
    public String insertGame(@RequestBody MultipleChoiceGameRequest request) throws JsonProcessingException {
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

        ArrayNode answersArray = rootObject.putArray("answers");
        List<String> answerText=request.getAnswerText();
        List<String> answerImage= request.getAnswerImage();
        List<Boolean> answerIsCorrect=request.getAnswerIsCorrect();
        for(int i=0;i<answerText.size();i++){
            ObjectNode answerObject=answersArray.addObject();
            answerObject.put("text",answerText.get(i));
            answerObject.put("image",answerImage.get(i));
            answerObject.put("isCorrect",answerIsCorrect.get(i));
        }

        String json=objectMapper.writeValueAsString(rootObject);
        System.out.println(json);
        multipleGameRepository.insertMultipleGame(json);
        return "successfully";
    }
    @GetMapping
    ResponseEntity<List<MultipleGame>> getAllData(){
        List<MultipleGame> games=multipleGameRepository.getAllData();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    @GetMapping("/data")
    ResponseEntity<MultipleGame> getByRandom(){
        List<MultipleGame> games=multipleGameRepository.getAllData();
        int index=new Random().nextInt(games.size());
        MultipleGame multipleGame=games.get(index);
        return new ResponseEntity<>(multipleGame, HttpStatus.OK);
    }

}
