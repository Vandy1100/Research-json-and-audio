package com.example.upload.game.json.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class Province {
    private String city;
    private String village;
}
