package com.ben.cloverserver.repository;

import com.ben.cloverserver.model.Friend;
import com.ben.cloverserver.repository.friend.FriendRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendRepository extends MongoRepository<Friend, String>, FriendRepositoryCustom {
}
