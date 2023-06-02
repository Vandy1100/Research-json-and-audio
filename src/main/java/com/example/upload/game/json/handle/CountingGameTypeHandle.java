package com.example.upload.game.json.handle;

import com.example.upload.game.json.model.CountingGame;
import com.example.upload.game.json.model.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountingGameTypeHandle extends BaseTypeHandler<CountingGame> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CountingGame parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCountingGame().toString());
    }

    @Override
    public CountingGame getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getJsonData(rs.getString(columnName));
    }

    @Override
    public CountingGame getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getJsonData(rs.getString(columnIndex));
    }

    @Override
    public CountingGame getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getJsonData(cs.getString(columnIndex));
    }

    private CountingGame getJsonData(String game) {
        if (game == null) {
            return null;
        }

        CountingGame result = new CountingGame();
        try {
            result.setCountingGame(OBJECT_MAPPER.readTree(game));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
        return result;
    }
}
