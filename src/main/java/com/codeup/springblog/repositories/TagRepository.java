package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Genre;
import com.codeup.springblog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("from Tag t where t.name like %:term%")
    List<Tag> searchByTagLike(@Param("term") String term);
}
