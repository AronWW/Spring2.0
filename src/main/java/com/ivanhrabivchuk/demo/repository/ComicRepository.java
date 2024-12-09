package com.ivanhrabivchuk.demo.repository;

import com.ivanhrabivchuk.demo.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
    List<Comic> findByAuthorId(Long authorId);
    List<Comic> findByGenre(String genre);
    Long countByAuthorId(Long authorId);
}