package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.service.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @ApiOperation(value = "List all existing books")
    @GetMapping(path = "/book", produces = "application/json")
    public List<Book> all(@RequestParam (value = "filter", defaultValue = "") String filter){
        return bookService.findAll(filter);
    }

    @ApiOperation(value = "Get a book by its ID", response = Book.class)
    @GetMapping(path = "/book/{id}", produces = "application/json")
    public Book one(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @ApiOperation(value = "Create a new book", response = Book.class)
    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public Book createBook(@RequestBody @Valid Book newBook, @ApiIgnore Authentication authentication){
        return bookService.addBook(newBook, authentication);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a book by its ID")
    @PutMapping(path = "/book/{id}", produces = "application/json")
    public void updateBook (@PathVariable Integer id, @RequestBody Book updBook, @ApiIgnore Authentication authentication){
        bookService.updateBook(updBook, authentication, id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a book by its ID")
    @DeleteMapping(path = "/book/{id}", produces = "application/json")
    public void deleteBook(@PathVariable Integer id, @ApiIgnore Authentication authentication){
        bookService.deleteBookById(id, authentication);
    }
}

