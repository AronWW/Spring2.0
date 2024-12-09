package com.ivanhrabivchuk.demo.controller.api;


import com.ivanhrabivchuk.demo.dto.RatingDTO;
import com.ivanhrabivchuk.demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.createRating(ratingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(
            @PathVariable Long id,
            @RequestBody RatingDTO ratingDTO
    ) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comic/{comicId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByComic(@PathVariable Long comicId) {
        return ResponseEntity.ok(ratingService.getRatingsByComic(comicId));
    }
}