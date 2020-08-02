package com.ben.cloverserver.service.impl;

import com.ben.cloverserver.model.Participant;
import com.ben.cloverserver.repository.ParticipantRepository;
import com.ben.cloverserver.service.base.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant insertEntity(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant updateEntity(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public void removeEntity(String id) {
        participantRepository.deleteById(id);
    }

    @Override
    public List<Participant> getAllEntities() {
        return participantRepository.findAll();
    }
}
