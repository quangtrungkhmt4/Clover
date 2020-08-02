package com.ben.cloverserver.service.base;

import com.ben.cloverserver.entity.SearchFriend;
import com.ben.cloverserver.model.Friend;

import java.util.List;

public interface FriendService extends Service<Friend> {

    List<SearchFriend> getAllFriendByUserId(String userId, int skip, int limit);

    List<String> getAllFriendIds(String userId);

}
