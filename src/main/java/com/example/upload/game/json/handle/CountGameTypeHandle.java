package com.example.upload.game.json.handle;

import com.example.upload.game.json.model.CountGame;
import com.example.upload.game.json.model.MultipleGame;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountGameTypeHandle extends BaseTypeHandler<CountGame> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CountGame parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCount_game_data().toString());
    }

    @Override
    public CountGame getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getJsonData(rs.getString(columnName));
    }

    @Override
    public CountGame getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getJsonData(rs.getString(columnIndex));
    }

    @Override
    public CountGame getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getJsonData(cs.getString(columnIndex));
    }

    private CountGame getJsonData(String json) {
        if (json == null) {
            return null;
        }

        CountGame result = new CountGame();
        try {
            result.setCount_game_data(OBJECT_MAPPER.readTree(json));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
        return result;
    }
}
