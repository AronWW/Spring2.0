package com.ivanhrabivchuk.demo.mapper;

import com.ivanhrabivchuk.demo.dto.ComicDTO;
import com.ivanhrabivchuk.demo.entity.Comic;
import com.ivanhrabivchuk.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComicMapper {
    private final AuthorRepository authorRepository;

    public ComicDTO toDto(Comic comic) {
        ComicDTO dto = new ComicDTO();
        dto.setId(comic.getId());
        dto.setTitle(comic.getTitle());
        dto.setDescription(comic.getDescription());
        dto.setPublicationDate(comic.getPublicationDate());
        dto.setGenre(comic.getGenre());
        dto.setCoverImageUrl(comic.getCoverImageUrl());
        dto.setIsCompleted(comic.getIsCompleted());
        dto.setAuthorId(comic.getAuthor() != null ? comic.getAuthor().getId() : null);
        dto.setAuthorName(comic.getAuthor() != null ? comic.getAuthor().getName() : null);
        return dto;
    }

    public Comic toEntity(ComicDTO dto) {
        Comic comic = new Comic();
        comic.setId(dto.getId());
        comic.setTitle(dto.getTitle());
        comic.setDescription(dto.getDescription());
        comic.setPublicationDate(dto.getPublicationDate());
        comic.setGenre(dto.getGenre());
        comic.setCoverImageUrl(dto.getCoverImageUrl());
        comic.setIsCompleted(dto.getIsCompleted());
        if (dto.getAuthorId() != null) {
            comic.setAuthor(authorRepository.findById(dto.getAuthorId()).orElse(null));
        }

        return comic;
    }
}
