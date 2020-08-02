package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.model.RequestAddFriend;
import com.ben.cloverserver.repository.RequestAddFriendRepository;
import com.ben.cloverserver.service.base.RequestAddFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestAddFriendServiceImpl implements RequestAddFriendService {

    @Autowired
    private RequestAddFriendRepository requestAddFriendRepository;

    @Override
    public RequestAddFriend insertEntity(RequestAddFriend requestAddFriend) {
        return requestAddFriendRepository.save(requestAddFriend);
    }

    @Override
    public RequestAddFriend updateEntity(RequestAddFriend requestAddFriend) {
        return requestAddFriendRepository.save(requestAddFriend);
    }

    @Override
    public void removeEntity(String id) {
        requestAddFriendRepository.deleteById(id);
    }

    @Override
    public List<RequestAddFriend> getAllEntities() {
        return requestAddFriendRepository.findAll();
    }
}
