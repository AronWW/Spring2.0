package com.ivanhrabivchuk.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;


    @Column(length = 1000)
    private String review;

    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comic;

    public void validateScore(){
        if (score < 1) score = 1;
        if (score > 10) score = 10;
    }
}