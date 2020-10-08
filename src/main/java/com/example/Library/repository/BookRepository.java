package com.example.Library.repository;

import com.example.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    Book getById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET name =:name, authorid =:authorid, about =:about, tagid =:tagid WHERE id =:id", nativeQuery = true)
    void updateBook(String name, Integer authorid, String about, Integer tagid, Integer id);

    @Query(value = "SELECT id, name, authorid, about, tagid " +
            "FROM book WHERE position(LOWER(:filter) in LOWER(about))>0 OR position(LOWER(:filter) in LOWER(name))>0", nativeQuery = true)
    List<Book> findAllUsingFilter(String filter);

    @Transactional
    void deleteBookById(Integer id);
}
