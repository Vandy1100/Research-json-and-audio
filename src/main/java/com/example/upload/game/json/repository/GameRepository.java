package com.example.upload.game.json.repository;

import com.example.upload.game.json.handle.GameTypeHandle;
import com.example.upload.game.json.model.Game;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
@Repository
public interface GameRepository {
    @Insert("INSERT INTO game_tb(game_data) VALUES (#{json}::jsonb)")
    void insertGame(@Param("json") String json);
    @Select("SELECT * FROM game_tb")
    @Result(column = "game_data",property = "game_data",typeHandler = GameTypeHandle.class)
    public List<Game> getAllData();

    @Select("SELECT * FROM game_tb WHERE game_data->'image'->>'title' = #{game}")
    @Result(column = "game_data",property = "game_data",typeHandler = GameTypeHandle.class)
    Game getGameById(@Param("game") String game);
}
