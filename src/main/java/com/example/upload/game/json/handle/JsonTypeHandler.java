package com.example.upload.game.json.handle;

import com.example.upload.game.json.model.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonTypeHandler extends BaseTypeHandler<JsonData> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonData parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getJson_data().toString());
    }

    @Override
    public JsonData getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getJsonData(rs.getString(columnName));
    }

    @Override
    public JsonData getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getJsonData(rs.getString(columnIndex));
    }

    @Override
    public JsonData getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getJsonData(cs.getString(columnIndex));
    }

    private JsonData getJsonData(String json) {
        if (json == null) {
            return null;
        }

        JsonData result = new JsonData();
        try {
            result.setJson_data(OBJECT_MAPPER.readTree(json));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
        return result;
    }
}