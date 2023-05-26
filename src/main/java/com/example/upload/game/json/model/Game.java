package com.example.upload.game.json.model;

import com.example.upload.game.json.model.response.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Game {
    private int id;
    private Object json_data;

}
