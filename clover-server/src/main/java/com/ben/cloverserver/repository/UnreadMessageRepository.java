package com.ben.cloverserver.repository;

import com.ben.cloverserver.model.UnreadMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnreadMessageRepository extends MongoRepository<UnreadMessage, String> {
}
