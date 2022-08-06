package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("from Genre g where g.name like %:term%")
    List<Genre> searchByGenreLike(@Param("term") String term);
}
