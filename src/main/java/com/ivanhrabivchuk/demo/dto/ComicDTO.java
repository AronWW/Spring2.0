package com.ivanhrabivchuk.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ComicDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private String genre;
    private Double averageRating;
    private Long authorId;
    private String authorName;
}