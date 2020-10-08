package com.example.Library.service;

import com.example.Library.entity.Book;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookService {
    List<Book> findAll(String filter);
    Book getById(Integer id);
    Book addBook(Book newBook, Authentication authentication);
    void updateBook(Book updBook, Authentication authentication, Integer id);
    void deleteBookById(Integer id, Authentication authentication);
}
