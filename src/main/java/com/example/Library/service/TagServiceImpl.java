package com.example.Library.service;

import com.example.Library.entity.Tag;
import com.example.Library.exception.NotFoundException;
import com.example.Library.exception.ParameterMissingException;
import com.example.Library.exception.WrongParameterException;
import com.example.Library.repository.TagRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    static Log log = LogFactory.getLog(TagServiceImpl.class.getName());

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag addTag(Tag newTag) {
        Tag tmpTag = new Tag();
        tmpTag.setName(newTag.getName());
        try {
            return tagRepository.save(tmpTag);
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }

    @Override
    public Tag getById(Integer id) {
        if(tagRepository.getById(id) != null)
            return tagRepository.getById(id);
        else throw new NotFoundException("Tag",id);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTagById(Integer id) {
        if (tagRepository.getById(id) == null){
            throw new NotFoundException("Tag",id);
        }
        try{
            tagRepository.deleteTagById(id);
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }

    @Override
    public void updateTag(Integer id, Tag updTag) {
        if (tagRepository.getById(id) == null){
            throw new NotFoundException("Tag",id);
        }
        Tag beforeUpdateTag = tagRepository.getById(id);
        if (updTag.getName() == null || updTag.getName().isEmpty()) {
            updTag.setName(beforeUpdateTag.getName());
        }
        try {
            tagRepository.updateTag(id, updTag.getName());
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }
}
