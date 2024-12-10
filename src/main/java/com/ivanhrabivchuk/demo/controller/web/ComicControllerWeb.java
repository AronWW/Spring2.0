package com.ivanhrabivchuk.demo.controller.web;

import com.ivanhrabivchuk.demo.dto.AuthorDTO;
import com.ivanhrabivchuk.demo.dto.ComicDTO;
import com.ivanhrabivchuk.demo.service.AuthorService;
import com.ivanhrabivchuk.demo.service.ComicService;
import com.ivanhrabivchuk.demo.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/comics")
@RequiredArgsConstructor
public class ComicControllerWeb {
    private final ComicService comicService;
    private final AuthorService authorService;
    private final RatingService ratingService;

    @GetMapping
    public String listComics(Model model) {
        model.addAttribute("comics", comicService.getAllComics());
        return "comics/list";
    }

    @GetMapping("/create")
    public String showCreateComicForm(Model model) {
        model.addAttribute("comic", new ComicDTO());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "comics/create";
    }

    @PostMapping("/create")
    public String createComic(@ModelAttribute ComicDTO comicDTO) {
        comicService.createComic(comicDTO);
        return "redirect:/web/comics";
    }

    @GetMapping("/{id}")
    public String comicDetails(@PathVariable Long id, Model model) {
        ComicDTO comic = comicService.getComicById(id);
        model.addAttribute("comic", comic);
        model.addAttribute("ratings", ratingService.getRatingsByComic(id));
        return "comics/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditComicForm(@PathVariable Long id, Model model) {
        ComicDTO comic = comicService.getComicById(id);
        List<AuthorDTO> authors = authorService.getAllAuthors();
        model.addAttribute("comic", comic);
        model.addAttribute("authors", authors);
        return "comics/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateComic(@PathVariable Long id, @ModelAttribute ComicDTO comicDTO) {
        comicService.updateComic(id, comicDTO);
        return "redirect:/web/comics/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteComic(@PathVariable Long id) {
        comicService.deleteComic(id);
        return "redirect:/web/comics";
    }

    @GetMapping("/top10")
    public String getTop10Comics(Model model) {
        model.addAttribute("comics", comicService.getTop10Comics());
        return "comics/top10";
    }
}
