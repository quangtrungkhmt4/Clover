package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.model.UnreadMessage;
import com.ben.cloverserver.repository.UnreadMessageRepository;
import com.ben.cloverserver.service.base.UnreadMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnreadMessageServiceImpl implements UnreadMessageService {

    @Autowired
    private UnreadMessageRepository unreadMessageRepository;

    @Override
    public UnreadMessage insertEntity(UnreadMessage unreadMessage) {
        return unreadMessageRepository.save(unreadMessage);
    }

    @Override
    public UnreadMessage updateEntity(UnreadMessage unreadMessage) {
        return unreadMessageRepository.save(unreadMessage);
    }

    @Override
    public void removeEntity(String id) {
        unreadMessageRepository.deleteById(id);
    }

    @Override
    public List<UnreadMessage> getAllEntities() {
        return unreadMessageRepository.findAll();
    }
}
