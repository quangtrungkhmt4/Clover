package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.model.Message;
import com.ben.cloverserver.repository.MessageRepository;
import com.ben.cloverserver.service.base.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message insertEntity(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateEntity(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void removeEntity(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getAllEntities() {
        return messageRepository.findAll();
    }
}
