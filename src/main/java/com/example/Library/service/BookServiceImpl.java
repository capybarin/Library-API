package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.exception.*;
import com.example.Library.repository.AuthorRepository;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.TagRepository;
import com.example.Library.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    static Log log = LogFactory.getLog(BookServiceImpl.class.getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Book> findAll(String filter) {
        if (!filter.equals("")){
            return bookRepository.findAllUsingFilter(filter).stream()
                    .sorted(Comparator.comparing(x -> x.getTag().getName(), String::compareToIgnoreCase))
                    .collect(Collectors.toList());
        } else {
            return bookRepository.findAll().stream()
                    .sorted(Comparator.comparing(x -> x.getTag().getName(), String::compareToIgnoreCase))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Book getById(Integer id) {
        Book book = bookRepository.getById(id);
        if (book != null)
            return book;
        else throw new NotFoundException("Book",id);
    }

    @Override
    public Book addBook(Book newBook, Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).getRoleId().getName().equals("READER")) {
            throw new ForbiddenException("Readers cannot add new books");
        }
        Book tmpBook = new Book();
        if (newBook.getName() == null || newBook.getName().isEmpty()) {
            throw new ParameterMissingException("name");
        } else {
            tmpBook.setName(newBook.getName());
        }
        if (newBook.getAuthor() == null){
            throw new ParameterMissingException("author object with id");
        } else {
            if (newBook.getAuthor().getId() == null || newBook.getAuthor().getId().toString().isEmpty()){
                throw new ParameterMissingException("author object with id");
            } else {
                tmpBook.setAuthor(authorRepository.getById(newBook.getAuthor().getId()));
            }
        }
        if (newBook.getAbout() == null || newBook.getAbout().isEmpty()) {
            throw new ParameterMissingException("about");
        } else {
            tmpBook.setAbout(newBook.getAbout());
        }
        if (newBook.getTag() == null){
            throw new ParameterMissingException("tag object with id");
        } else {
            if (newBook.getTag().getId() == null || newBook.getTag().getId().toString().isEmpty()){
                throw new ParameterMissingException("tag object with id");
            } else {
                tmpBook.setTag(tagRepository.getById(newBook.getTag().getId()));
            }
        }
        try {
            return bookRepository.save(tmpBook);
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }

    @Override
    public void updateBook(Book updBook, Authentication authentication, Integer id) {
        if (userRepository.findByEmail(authentication.getName()).getRoleId().getName().equals("READER")) {
            throw new ForbiddenException("Readers cannot update existing books");
        }
        if (bookRepository.getById(id) == null) {
            throw new NotFoundException("Book",id);
        }
        Book beforeUpdateBook = bookRepository.getById(id);
        if (updBook.getName() == null || updBook.getName().isEmpty()) {
            updBook.setName(beforeUpdateBook.getName());
        }
        if (updBook.getAuthor() == null){
            updBook.setAuthor(beforeUpdateBook.getAuthor());
        } else {
            if (updBook.getAuthor().getId() == null || updBook.getAuthor().getId().toString().isEmpty()){
                updBook.setAuthor(beforeUpdateBook.getAuthor());
            }
        }
        if (updBook.getAbout() == null || updBook.getAbout().isEmpty()) {
            updBook.setAbout(beforeUpdateBook.getAbout());
        }
        if (updBook.getTag() == null){
            updBook.setTag(beforeUpdateBook.getTag());
        } else {
            if (updBook.getTag().getId() == null || updBook.getTag().getId().toString().isEmpty()){
                updBook.setTag(beforeUpdateBook.getTag());
            }
        }
        try {
            bookRepository.updateBook(updBook.getName(), updBook.getAuthor().getId(), updBook.getAbout(), updBook.getTag().getId(), id);
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }

    @Override
    public void deleteBookById(Integer id, Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).getRoleId().getName().equals("READER")) {
            throw new ForbiddenException("Readers cannot delete existing books");
        }
        if (bookRepository.getById(id) == null) {
            throw new NotFoundException("Book",id);
        }
        try{
            bookRepository.deleteBookById(id);
        }catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }
}
