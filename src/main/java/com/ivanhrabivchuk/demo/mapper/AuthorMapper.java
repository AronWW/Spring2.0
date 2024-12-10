package com.ivanhrabivchuk.demo.mapper;

import com.ivanhrabivchuk.demo.dto.AuthorDTO;
import com.ivanhrabivchuk.demo.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorDTO toDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBiography(author.getBiography());
        dto.setImageUrl(author.getImageUrl());
        return dto;
    }

    public Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());
        author.setImageUrl(dto.getImageUrl());
        return author;
    }
}