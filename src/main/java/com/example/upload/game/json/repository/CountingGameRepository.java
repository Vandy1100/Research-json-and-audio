package com.example.upload.game.json.repository;

import com.example.upload.game.json.handle.CountingGameTypeHandle;
import com.example.upload.game.json.model.CountingGame;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CountingGameRepository {
    @Insert("INSERT INTO counting_game_tb(counting_game_data) VALUES (#{json}::jsonb)")
    void insertCountingGame(@Param("json") String json);

    @Select("SELECT * FROM counting_game_tb")
    @Result(column = "counting_game_data",property = "countingGame",typeHandler = CountingGameTypeHandle.class)
    List<CountingGame> getAllCountingGame();
}
