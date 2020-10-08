package com.example.Library.service;

import com.example.Library.entity.Tag;

import java.util.List;

public interface TagService {
    Tag addTag(Tag newTag);
    Tag getById(Integer id);
    List<Tag> findAll();
    void deleteTagById(Integer id);
    void updateTag(Integer id, Tag updTag);
}
