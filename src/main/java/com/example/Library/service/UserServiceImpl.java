package com.example.Library.service;

import com.example.Library.entity.User;
import com.example.Library.exception.NotFoundException;
import com.example.Library.exception.WrongParameterException;
import com.example.Library.repository.RoleRepository;
import com.example.Library.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    static Log log = LogFactory.getLog(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        User user = userRepository.getById(id);
        if (user != null)
            return user;
        else throw new NotFoundException("User", id);
    }

    @Override
    public User addUser(User newUser) {
        User tmpUser = new User();
        tmpUser.setFirstName(newUser.getFirstName());
        tmpUser.setLastName(newUser.getLastName());
        tmpUser.setRoleId(roleRepository.findByName("READER"));
        tmpUser.setEmail(newUser.getEmail());
        tmpUser.setPassword(newUser.getPassword());
        try {
            return userRepository.save(tmpUser);
        } catch (Exception e){
            log.error(e);
            throw new WrongParameterException("Something went wrong");
        }
    }
}
