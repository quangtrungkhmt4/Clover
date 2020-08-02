package com.ben.cloverserver.repository;

import com.ben.cloverserver.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipantRepository extends MongoRepository<Participant, String> {
}
