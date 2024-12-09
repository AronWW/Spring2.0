package com.ivanhrabivchuk.demo.repository;

import com.ivanhrabivchuk.demo.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByComicId(Long comicId);
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.comic.id = :comicId")
    Double findAverageScoreByComicId(@Param("comicId") Long comicId);
}