package com.ivanhrabivchuk.demo.controller.web;

import com.ivanhrabivchuk.demo.dto.ComicDTO;
import com.ivanhrabivchuk.demo.dto.RatingDTO;
import com.ivanhrabivchuk.demo.service.ComicService;
import com.ivanhrabivchuk.demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/web/ratings")
@RequiredArgsConstructor
public class RatingControllerWeb {
    private final RatingService ratingService;
    private final ComicService comicService;

    @GetMapping("/create/{comicId}")
    public String showCreateRatingForm(@PathVariable Long comicId, Model model) {
        ComicDTO comic = comicService.getComicById(comicId);
        RatingDTO rating = new RatingDTO();
        rating.setComicId(comicId);

        model.addAttribute("rating", rating);
        model.addAttribute("comic", comic);
        return "ratings/create";
    }

    @PostMapping("/create")
    public String createRating(@ModelAttribute RatingDTO ratingDTO) {
        ratingDTO.setRatingDate(LocalDateTime.now());
        ratingService.createRating(ratingDTO);
        return "redirect:/web/comics/" + ratingDTO.getComicId();
    }

    @GetMapping("/{id}/edit")
    public String showEditRatingForm(@PathVariable Long id, Model model) {
        RatingDTO rating = ratingService.getRatingById(id);
        ComicDTO comic = comicService.getComicById(rating.getComicId());

        model.addAttribute("rating", rating);
        model.addAttribute("comic", comic);
        return "ratings/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateRating(@PathVariable Long id, @ModelAttribute RatingDTO ratingDTO) {
        ratingService.updateRating(id, ratingDTO);
        return "redirect:/web/comics/" + ratingDTO.getComicId();
    }

    @GetMapping("/{id}/delete")
    public String deleteRating(@PathVariable Long id, @RequestParam Long comicId) {
        ratingService.deleteRating(id);
        return "redirect:/web/comics/" + comicId;
    }
}
