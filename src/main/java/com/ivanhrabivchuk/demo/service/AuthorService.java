package com.ivanhrabivchuk.demo.service;


import com.ivanhrabivchuk.demo.dto.AuthorDTO;
import com.ivanhrabivchuk.demo.entity.Author;
import com.ivanhrabivchuk.demo.entity.Comic;
import com.ivanhrabivchuk.demo.mapper.AuthorMapper;
import com.ivanhrabivchuk.demo.repository.AuthorRepository;
import com.ivanhrabivchuk.demo.repository.ComicRepository;
import com.ivanhrabivchuk.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ComicRepository comicRepository;
    private final RatingRepository ratingRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDto(savedAuthor);
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.toDto(author);
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Optional.ofNullable(authorDTO.getName()).ifPresent(existingAuthor::setName);
        Optional.ofNullable(authorDTO.getBiography()).ifPresent(existingAuthor::setBiography);
        Optional.ofNullable(authorDTO.getImageUrl()).ifPresent(existingAuthor::setImageUrl);

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.toDto(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Long getComicsCountByAuthor(Long authorId) {
        return comicRepository.countByAuthorId(authorId);
    }

    public Double getAverageRatingForAuthor(Long authorId) {
        return ratingRepository.findAverageRatingByAuthorId(authorId);
    }
}