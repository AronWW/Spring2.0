package com.ivanhrabivchuk.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "rating_date")
    private LocalDateTime ratingDate;

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