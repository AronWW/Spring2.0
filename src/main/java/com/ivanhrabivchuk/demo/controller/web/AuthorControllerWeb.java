package com.ivanhrabivchuk.demo.controller.web;

import com.ivanhrabivchuk.demo.dto.AuthorDTO;
import com.ivanhrabivchuk.demo.service.AuthorService;
import com.ivanhrabivchuk.demo.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/authors")
@RequiredArgsConstructor
public class AuthorControllerWeb {
    private final AuthorService authorService;
    private final ComicService comicService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list";
    }

    @GetMapping("/create")
    public String showCreateAuthorForm(Model model) {
        model.addAttribute("author", new AuthorDTO());
        return "authors/create";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute AuthorDTO authorDTO) {
        authorService.createAuthor(authorDTO);
        return "redirect:/web/authors";
    }

    @GetMapping("/{id}")
    public String authorDetails(@PathVariable Long id, Model model) {
        AuthorDTO author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("comics", comicService.getComicsByAuthor(id));
        model.addAttribute("comicsCount", authorService.getComicsCountByAuthor(id));
        model.addAttribute("averageRating", authorService.getAverageRatingForAuthor(id));
        return "authors/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "authors/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute AuthorDTO authorDTO) {
        authorService.updateAuthor(id, authorDTO);
        return "redirect:/web/authors/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/web/authors";
    }
}
