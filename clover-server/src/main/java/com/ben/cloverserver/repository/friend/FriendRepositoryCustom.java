package com.ben.cloverserver.repository.friend;

import com.ben.cloverserver.entity.SearchFriend;
import com.ben.cloverserver.model.Friend;

import java.util.List;

public interface FriendRepositoryCustom {
    List<SearchFriend> getAllFriendByUserId(String userId, int skip, int limit);
    List<String> getAllFriendIds(String userId);
}
