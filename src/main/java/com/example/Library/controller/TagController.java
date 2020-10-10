package com.example.Library.controller;

import com.example.Library.entity.Tag;
import com.example.Library.service.TagServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagServiceImpl tagService;

    @ApiOperation(value = "Create a new tag", response = Tag.class)
    @PostMapping(path = "/tag", produces = "application/json", consumes = "application/json")
    public Tag newTag(@RequestBody @Valid Tag newTag){
        return tagService.addTag(newTag);
    }

    @ApiOperation(value = "View a list of all tags")
    @GetMapping(path = "/tag", produces = "application/json")
    public List<Tag> all(){
        return tagService.findAll();
    }

    @ApiOperation(value = "Get a tag by its ID", response = Tag.class)
    @GetMapping(path = "/tag/{id}", produces = "application/json")
    public Tag one(@PathVariable Integer id){
        return tagService.getById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a tag by its ID")
    @PutMapping(path = "/tag/{id}", produces = "application/json", consumes = "application/json")
    public void updateTag(@PathVariable Integer id, @RequestBody @Valid Tag newTag){
        tagService.updateTag(id, newTag);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a tag by its ID")
    @DeleteMapping(path = "/tag/{id}", produces = "application/json")
    public void deleteTag(@PathVariable Integer id){
        tagService.deleteTagById(id);
    }
}
