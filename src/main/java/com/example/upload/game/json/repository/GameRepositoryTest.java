package com.example.upload.game.json.repository;

import com.example.upload.game.json.model.Game1;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepositoryTest extends JpaRepository<Game1, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO test1_tb (json_data) VALUES (CAST(:jsonData AS jsonb))", nativeQuery = true)
    void insertData(@Param("jsonData") String jsonData);

}
