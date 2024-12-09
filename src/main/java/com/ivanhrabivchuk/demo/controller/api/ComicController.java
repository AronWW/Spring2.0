package com.ivanhrabivchuk.demo.controller.api;


import com.ivanhrabivchuk.demo.dto.ComicDTO;
import com.ivanhrabivchuk.demo.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comics")
@RequiredArgsConstructor
public class ComicController {
    private final ComicService comicService;

    @PostMapping
    public ResponseEntity<ComicDTO> createComic(@RequestBody ComicDTO comicDTO) {
        return ResponseEntity.ok(comicService.createComic(comicDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicDTO> getComicById(@PathVariable Long id) {
        ComicDTO comicDTO = comicService.getComicById(id);
        return ResponseEntity.ok(comicDTO);
    }

    @GetMapping
    public ResponseEntity<List<ComicDTO>> getAllComics() {
        return ResponseEntity.ok(comicService.getAllComics());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicDTO> updateComic(
            @PathVariable Long id,
            @RequestBody ComicDTO comicDTO
    ) {
        return ResponseEntity.ok(comicService.updateComic(id, comicDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComic(@PathVariable Long id) {
        comicService.deleteComic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top10")
    public ResponseEntity<List<ComicDTO>> getTop10Comics() {
        List<ComicDTO> topComics = comicService.getTop10Comics();
        return ResponseEntity.ok(topComics);
    }

    @GetMapping("/by-author/{authorId}")
    public ResponseEntity<List<ComicDTO>> getComicsByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(comicService.getComicsByAuthor(authorId));
    }

    @GetMapping("/by-genre")
    public ResponseEntity<List<ComicDTO>> getComicsByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(comicService.getComicsByGenre(genre));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<ComicDTO>> getComicsSortedByDate() {
        return ResponseEntity.ok(comicService.getComicsSortedByDate());
    }
}