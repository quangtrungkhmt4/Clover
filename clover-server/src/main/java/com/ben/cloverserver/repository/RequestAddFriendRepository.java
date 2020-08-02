package com.ben.cloverserver.repository;

import com.ben.cloverserver.model.RequestAddFriend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestAddFriendRepository extends MongoRepository<RequestAddFriend, String> {
}
