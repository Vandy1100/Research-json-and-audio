package com.example.upload.game.json.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountGameRequest {
    private String sound;
    private String question;
    private int countNumber;
    private String image;
    private String title;
}
