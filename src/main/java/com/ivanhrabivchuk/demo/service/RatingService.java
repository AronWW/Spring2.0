package com.ivanhrabivchuk.demo.service;


import com.ivanhrabivchuk.demo.dto.RatingDTO;
import com.ivanhrabivchuk.demo.entity.Rating;
import com.ivanhrabivchuk.demo.mapper.RatingMapper;
import com.ivanhrabivchuk.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingDTO createRating(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toEntity(ratingDTO);
        rating.validateScore();
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }

    public RatingDTO getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        return ratingMapper.toDto(rating);
    }

    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

    public RatingDTO updateRating(Long id, RatingDTO ratingDTO) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        existingRating.setScore(ratingDTO.getScore());
        if (ratingDTO.getScore() != null) {
            existingRating.setScore(ratingDTO.getScore());
        }
        if (ratingDTO.getReview() != null) {
            existingRating.setReview(ratingDTO.getReview());
        }

        Rating updatedRating = ratingRepository.save(existingRating);
        return ratingMapper.toDto(updatedRating);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public List<RatingDTO> getRatingsByComic(Long comicId) {
        List<Rating> ratings = ratingRepository.findByComicId(comicId);
        return ratings.stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }
}