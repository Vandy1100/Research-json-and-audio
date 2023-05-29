package com.example.upload.game.json.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameRequest {
    private String titleGame;
    private String description;
    private String title;
    private int score;
    private String url;
    private List<String> name;
}
