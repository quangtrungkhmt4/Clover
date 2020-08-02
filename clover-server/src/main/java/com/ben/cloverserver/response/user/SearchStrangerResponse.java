package com.ben.cloverserver.response.user;

import com.ben.cloverserver.response.AbstractResponse;
import com.ben.cloverserver.response.friend.FriendResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStrangerResponse extends AbstractResponse {
    List<FriendResponse> strangers;
    private int total;
}
