package com.ben.cloverserver.controller.user;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.request.user.SearchStrangerRequest;
import com.ben.cloverserver.response.ResponseEntity;
import com.ben.cloverserver.response.friend.FriendResponse;
import com.ben.cloverserver.response.friend.SearchFriendResponse;
import com.ben.cloverserver.response.user.SearchStrangerResponse;
import com.ben.cloverserver.service.base.FriendService;
import com.ben.cloverserver.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchStrangerAPI extends AbstractController<SearchStrangerRequest> {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    private String userId;

    private List<User> strangers;

    private int total;

    @PostMapping("/strangers")
    public String searchStrangers(@RequestBody SearchStrangerRequest request){
        return handle(request);
    }

    @Override
    protected void valid(SearchStrangerRequest request) {
        userId = getUserIdByToken(request.getToken());
        if (userId == null){
            throw new ApplicationException(ResponseCode.TOKEN_EXPIRED);
        }
    }

    @Override
    protected void process(SearchStrangerRequest request) {
        List<String> friendIds = friendService.getAllFriendIds(userId);
        friendIds.add(userId);
        strangers = userService.getStranger(friendIds, request.getPage(), request.getTake());
        total = userService.getTotalStranger(friendIds);
    }

    @Override
    protected String createResponse() {
        List<FriendResponse> friendResponses = new ArrayList<>();
        strangers.forEach(stranger -> {
            FriendResponse friendResponse = FriendResponse.builder()
                    .friendId(stranger.getId())
                    .dayOfBirth(stranger.getDayOfBirth())
                    .monthOfBirth(stranger.getMonthOfBirth())
                    .yearOfBirth(stranger.getYearOfBirth())
                    .gender(stranger.getGender())
                    .name(stranger.getName())
                    .avatar(stranger.getAvatar())
                    .status(stranger.getStatus()).build();
            friendResponses.add(friendResponse);
        });
        return new ResponseEntity(new SearchStrangerResponse(friendResponses, total)).toString();
    }
}
