package com.example.upload.game.json.handle;

import com.example.upload.game.json.model.Game;
import com.example.upload.game.json.model.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameTypeHandle extends BaseTypeHandler<Game> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Game parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getGame_data().toString());
    }

    @Override
    public Game getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getJsonData(rs.getString(columnName));
    }

    @Override
    public Game getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getJsonData(rs.getString(columnIndex));
    }

    @Override
    public Game getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getJsonData(cs.getString(columnIndex));
    }

    private Game getJsonData(String game) {
        if (game == null) {
            return null;
        }

        Game result = new Game();
        try {
            result.setGame_data(OBJECT_MAPPER.readTree(game));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
        return result;
    }
}
