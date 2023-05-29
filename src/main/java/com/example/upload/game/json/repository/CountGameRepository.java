package com.example.upload.game.json.repository;

import com.example.upload.game.json.handle.CountGameTypeHandle;
import com.example.upload.game.json.model.CountGame;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CountGameRepository {

    @Insert("INSERT INTO count_game_tb (count_game_data) VALUES (#{json}::jsonb)")
    void insertCountGame(@Param("json") String json);
    @Select("SELECT * FROM count_game_tb")
    @Result(column = "count_game_data",property = "count_game_data",typeHandler = CountGameTypeHandle.class)
    List<CountGame> getAllCountGame();
}
