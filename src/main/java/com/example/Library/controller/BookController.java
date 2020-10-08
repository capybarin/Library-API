package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(path = "/book", produces = "application/json")
    public List<Book> all(@RequestParam (value = "filter", defaultValue = "") String filter){
        return bookService.findAll(filter);
    }

    @GetMapping(path = "/book/{id}", produces = "application/json")
    public Book one(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public Book createBook(@RequestBody Book newBook, Authentication authentication){
        return bookService.addBook(newBook, authentication);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "/book/{id}")
    public void updateBook (@PathVariable Integer id, @RequestBody Book updBook, Authentication authentication){
        bookService.updateBook(updBook, authentication, id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/book/{id}")
    public void deleteBook(@PathVariable Integer id, Authentication authentication){
        bookService.deleteBookById(id, authentication);
    }
}

