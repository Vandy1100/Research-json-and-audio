package com.example.upload.game.json.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponse {
    private String country;
    private Object province;
    private String city;
    private String village;
}
