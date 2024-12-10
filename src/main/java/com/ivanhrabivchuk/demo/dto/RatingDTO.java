package com.ivanhrabivchuk.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingDTO {
    private Long id;
    private Integer score;
    private String review;
    private LocalDateTime ratingDate;
    private Long comicId;
}