package com.example.Library.repository;

import com.example.Library.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAll();
    Tag getById(Integer id);

    @Transactional
    void deleteTagById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tag SET name =:name WHERE id =:id", nativeQuery = true)
    void updateTag(Integer id, String name);
}
