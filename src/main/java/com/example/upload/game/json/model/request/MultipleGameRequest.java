package com.example.upload.game.json.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MultipleGameRequest {
    private String sound;
    private String question;
    private String image;
    private List<String> word;
    private List<Boolean> isCorrect;
    private String titleGame;
    private String description;
}
