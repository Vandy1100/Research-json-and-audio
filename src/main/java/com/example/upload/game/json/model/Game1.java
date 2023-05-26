package com.example.upload.game.json.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test1_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Game1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "jsonb")
    private String jsonData;

    // constructors, getters, setters, etc.
}

