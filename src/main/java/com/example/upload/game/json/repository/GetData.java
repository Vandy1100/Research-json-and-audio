package com.example.upload.game.json.repository;

import com.example.upload.game.json.model.Game;
import com.example.upload.game.json.model.request.AddressRequest;
import com.example.upload.game.json.model.response.AddressResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Mapper
public interface GetData {
        @Insert("INSERT INTO test1_tb (json_data) VALUES (#{json}::jsonb)")
        void insertAddress(@Param("json") String json);
        @Select("SELECT json_data->'country' AS country,json_data->'province' AS province, json_data#>'{province,city}' AS city, json_data#>'{province,village}' AS village\n" +
                "FROM test1_tb")
        public List<AddressResponse> getAllData();
}
