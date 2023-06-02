package com.example.upload.game.json.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountingGameRequest {
    private String code;
    private int typeId;
    private String typeGame;
    private int level;
    private String instructionText;
    private String instructionVideo;
    private String instructionSound;
    private String instructionImage;
    private String questionSound;
    private String questionText;
    private String questionImage;
    private String questionVideo;
    private Integer questionValue;
    private List<Integer> answerCount;
}
