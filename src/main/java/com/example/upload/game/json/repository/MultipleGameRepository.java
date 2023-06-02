package com.example.upload.game.json.repository;

import com.example.upload.game.json.handle.MultipleGameTypeHandle;
import com.example.upload.game.json.model.MultipleGame;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MultipleGameRepository {

    @Insert("INSERT INTO multiple_game_tb (multiple_game_data) VALUES (#{json}::jsonb)")
    void insertMultipleGame(@Param("json") String json);
    @Select("SELECT * FROM multiple_game_tb")
    @Result(column = "multiple_game_data",property = "multiple_game_data",typeHandler = MultipleGameTypeHandle.class)
    List<MultipleGame> getAllData();
}
