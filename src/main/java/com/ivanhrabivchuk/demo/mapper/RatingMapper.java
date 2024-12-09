package com.ivanhrabivchuk.demo.mapper;


import com.ivanhrabivchuk.demo.dto.RatingDTO;
import com.ivanhrabivchuk.demo.entity.Rating;
import com.ivanhrabivchuk.demo.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingMapper {
    private final ComicRepository comicRepository;

    public RatingDTO toDto(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setScore(rating.getScore());
        dto.setReview(rating.getReview());
        dto.setComicId(rating.getComic() != null ? rating.getComic().getId() : null);
        return dto;
    }

    public Rating toEntity(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setScore(dto.getScore());
        rating.setReview(dto.getReview());
        rating.setComic(dto.getComicId() != null ?
                comicRepository.findById(dto.getComicId()).orElse(null) : null);
        return rating;
    }
}