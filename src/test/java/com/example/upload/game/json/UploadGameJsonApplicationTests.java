package com.example.upload.game.json;

import com.example.upload.game.json.model.Game;
import com.example.upload.game.json.model.request.AddressRequest;
import com.example.upload.game.json.repository.GetData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UploadGameJsonApplicationTests {
  @Autowired
    GetData getData;
    @Test
    void contextLoads() {
    }
    @Test
    void insertData(){
    }

}
