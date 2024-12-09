package com.ivanhrabivchuk.demo.service;


import com.ivanhrabivchuk.demo.dto.ComicDTO;
import com.ivanhrabivchuk.demo.entity.Comic;
import com.ivanhrabivchuk.demo.mapper.ComicMapper;
import com.ivanhrabivchuk.demo.repository.ComicRepository;
import com.ivanhrabivchuk.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;
    private final RatingRepository ratingRepository;

    public ComicDTO createComic(ComicDTO comicDTO) {
        Comic comic = comicMapper.toEntity(comicDTO);

        if (comic.getAuthor() != null) {
            comicDTO.setAuthorId(comic.getAuthor().getId());
        }

        Comic savedComic = comicRepository.save(comic);
        return comicMapper.toDto(savedComic);
    }

    public ComicDTO getComicById(Long id) {
        Comic comic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Комікс не знайдено"));

        Double averageRating = ratingRepository.findAverageScoreByComicId(id);

        ComicDTO comicDTO = comicMapper.toDto(comic);
        comicDTO.setAverageRating(averageRating != null ? averageRating : 0.0);
        return comicDTO;
    }

    public List<ComicDTO> getTop10Comics() {
        List<Comic> comics = comicRepository.findAll();

        return comics.stream()
                .map(this::mapToDtoWithAverageRating)
                .sorted(Comparator.comparing(ComicDTO::getAverageRating, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(10)
                .collect(Collectors.toList());
    }


    private ComicDTO mapToDtoWithAverageRating(Comic comic) {
        ComicDTO dto = comicMapper.toDto(comic);

        Double averageRating = ratingRepository.findAverageScoreByComicId(comic.getId());
        dto.setAverageRating(averageRating != null ? averageRating : 0.0);

        return dto;
    }

    public List<ComicDTO> getAllComics() {
        List<Comic> comics = comicRepository.findAll();
        return comics.stream()
                .map(this::mapToDtoWithAverageRating)
                .collect(Collectors.toList());
    }

    public ComicDTO updateComic(Long id, ComicDTO comicDTO) {
        Comic existingComic = comicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comic not found"));

        existingComic.setTitle(comicDTO.getTitle());
        existingComic.setDescription(comicDTO.getDescription());
        existingComic.setPublicationDate(comicDTO.getPublicationDate());
        existingComic.setGenre(comicDTO.getGenre());

        Comic updatedComic = comicRepository.save(existingComic);
        return comicMapper.toDto(updatedComic);
    }

    public void deleteComic(Long id) {
        comicRepository.deleteById(id);
    }

    public List<ComicDTO> getComicsByAuthor(Long authorId) {
        return comicRepository.findByAuthorId(authorId).stream()
                .map(this::mapToDtoWithAverageRating)
                .collect(Collectors.toList());
    }

    public List<ComicDTO> getComicsByGenre(String genre) {
        return comicRepository.findByGenre(genre).stream()
                .map(this::mapToDtoWithAverageRating)
                .collect(Collectors.toList());
    }

    public List<ComicDTO> getComicsSortedByDate() {
        return comicRepository.findAll().stream()
                .filter(comic -> comic.getPublicationDate() != null)
                .sorted(Comparator.comparing(Comic::getPublicationDate))
                .map(this::mapToDtoWithAverageRating)
                .collect(Collectors.toList());
    }
}