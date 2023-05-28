package com.example.upload.game.json.repository;

import com.example.upload.game.json.handle.JsonTypeHandler;
import com.example.upload.game.json.model.JsonData;
import com.example.upload.game.json.model.response.Address;
import com.example.upload.game.json.model.response.Province;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Mapper
public interface GetData {
        @Insert("INSERT INTO test1_tb (json_data) VALUES (#{json}::jsonb)")
        void insertAddress(@Param("json") String json);
//        @Select("SELECT json_data->>'country' AS country, json_data->'province' AS province FROM test1_tb")
//@Select("SELECT json_data->>'country' AS country, json_data->'province'->>'city' AS city, json_data->'province'->>'village' AS village FROM test1_tb")
//List<Address> getAllData();
//@Select("SELECT json_data->>'country' AS country, json_data#>>'{province,city}' AS city, json_data->'province'->>'village' AS village FROM test1_tb")
//public List<Address> getAllData();

        @Select("SELECT * FROM test1_tb")
        @Results({
                @Result( column = "json_data",property = "json_data",typeHandler = JsonTypeHandler.class)
        })
        public List<JsonData> getAllJson();
}
