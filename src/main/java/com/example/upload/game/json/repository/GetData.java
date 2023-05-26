package com.example.upload.game.json.repository;

import com.example.upload.game.json.model.Game1;
import com.example.upload.game.json.model.response.AddressResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Mapper
public interface GetData {
        @Select("SELECT json_data->'country' AS country, json_data#>'{province,city}' AS province, json_data#>'{province,village}' AS village\n" +
                "FROM test1_tb")
        public List<AddressResponse> getAllData();
}
