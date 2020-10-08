package com.example.Library.controller;

import com.example.Library.entity.Tag;
import com.example.Library.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagServiceImpl tagService;

    @PostMapping(path = "/tag", produces = "application/json", consumes = "application/json")
    public Tag newTag(@RequestBody Tag newTag){
        return tagService.addTag(newTag);
    }

    @GetMapping(path = "/tag", produces = "application/json")
    public List<Tag> all(){
        return tagService.findAll();
    }

    @GetMapping(path = "/tag/{id}", produces = "application/json")
    public Tag one(@PathVariable Integer id){
        return tagService.getById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "/tag/{id}", produces = "application/json", consumes = "application/json")
    public void updateTag(@PathVariable Integer id, @RequestBody Tag newTag){
        tagService.updateTag(id, newTag);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/tag/{id}")
    public void deleteTag(@PathVariable Integer id){
        tagService.deleteTagById(id);
    }
}
