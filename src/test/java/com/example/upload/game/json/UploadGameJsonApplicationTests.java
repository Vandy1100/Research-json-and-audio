package com.example.upload.game.json;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UploadGameJsonApplicationTests {
  @Autowired
    GameRepository gameRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void insertData(){
        Game game=new Game(4,"dara",23,"gg");
        gameRepository.insert(game);
    }

}
