package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.repository.UserRepository;
import com.ben.cloverserver.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User insertEntity(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateEntity(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeEntity(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllEntities() {
        return userRepository.findAll();
    }

    @Override
    public boolean isExistsUser(String userName) {
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public boolean isExistsMail(String mail) {
        return userRepository.existsUserByMail(mail);
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        if (user == null){
            throw new ApplicationException(ResponseCode.WRONG_USERNAME_OR_PASS);
        }
        return user;
    }

    @Override
    public User getUserByUserNameAndMail(String userName, String mail) {
        User user = userRepository.findUserByUserNameAndMail(userName, mail);
        if (user == null){
            throw new ApplicationException(ResponseCode.WRONG_USERNAME_AND_MAIL);
        }
        return user;
    }

    @Override
    public List<User> getStranger(List<String> friendIds, int page, int take) {
        Pageable pageable = PageRequest.of(page, take);
        return userRepository.findAllByUserIdIsNotIn(friendIds, pageable);
    }

    @Override
    public Integer getTotalStranger(List<String> friends) {
        return userRepository.countAllByUserIdIsNotIn(friends);
    }
}
