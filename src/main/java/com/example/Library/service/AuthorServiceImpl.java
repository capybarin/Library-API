package com.example.Library.service;

import com.example.Library.entity.Author;
import com.example.Library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author getById(Integer id) {
        return authorRepository.getById(id);
    }
}
