package com.ben.cloverserver.controller.friend;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.entity.SearchFriend;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.request.friend.SearchFriendRequest;
import com.ben.cloverserver.response.ResponseEntity;
import com.ben.cloverserver.response.friend.FriendResponse;
import com.ben.cloverserver.response.friend.SearchFriendResponse;
import com.ben.cloverserver.service.base.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetListFriendAPI extends AbstractController<SearchFriendRequest> {

    @Autowired
    private FriendService friendService;

    private String userId;

    private List<SearchFriend> searchFriends;

    @PostMapping("/friends")
    public String getListFriends(@RequestBody SearchFriendRequest request){
        return handle(request);
    }

    @Override
    protected void valid(SearchFriendRequest request) {
        userId = getUserIdByToken(request.getToken());
        if (userId == null){
            throw new ApplicationException(ResponseCode.TOKEN_EXPIRED);
        }
    }

    @Override
    protected void process(SearchFriendRequest request) {
        friendService.getAllFriendIds(userId);
        searchFriends = friendService.getAllFriendByUserId(userId, request.getSkip(), request.getTake());
    }

    @Override
    protected String createResponse() {
        List<FriendResponse> friendResponses = new ArrayList<>();
        searchFriends.forEach(friend -> {
            User info = friend.getInfo().get(0);
            FriendResponse friendResponse = FriendResponse.builder()
                    .friendId(friend.getFriendId())
                    .dayOfBirth(info.getDayOfBirth())
                    .monthOfBirth(info.getMonthOfBirth())
                    .yearOfBirth(info.getYearOfBirth())
                    .gender(info.getGender())
                    .name(info.getName())
                    .avatar(info.getAvatar())
                    .status(info.getStatus()).build();
            friendResponses.add(friendResponse);
        });
        return new ResponseEntity(new SearchFriendResponse(friendResponses)).toString();
    }
}
