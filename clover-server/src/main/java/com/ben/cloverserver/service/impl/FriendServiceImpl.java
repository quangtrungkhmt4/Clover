package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.entity.SearchFriend;
import com.ben.cloverserver.model.Friend;
import com.ben.cloverserver.repository.FriendRepository;
import com.ben.cloverserver.service.base.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public Friend insertEntity(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public Friend updateEntity(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void removeEntity(String id) {
        friendRepository.deleteById(id);
    }

    @Override
    public List<Friend> getAllEntities() {
        return friendRepository.findAll();
    }

    @Override
    public List<SearchFriend> getAllFriendByUserId(String userId, int skip, int limit) {
        return friendRepository.getAllFriendByUserId(userId, skip, limit);
    }

    @Override
    public List<String> getAllFriendIds(String userId) {
        return friendRepository.getAllFriendIds(userId);
    }
}
