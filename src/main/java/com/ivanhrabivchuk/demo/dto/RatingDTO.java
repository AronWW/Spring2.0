package com.ivanhrabivchuk.demo.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private Integer score;
    private String review;
    private Long comicId;
}