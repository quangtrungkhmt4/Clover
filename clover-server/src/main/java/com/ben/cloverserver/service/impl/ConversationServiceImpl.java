package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.model.Conversation;
import com.ben.cloverserver.repository.ConversationRepository;
import com.ben.cloverserver.service.base.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation insertEntity(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation updateEntity(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public void removeEntity(String id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public List<Conversation> getAllEntities() {
        return conversationRepository.findAll();
    }
}
