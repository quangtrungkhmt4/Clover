package com.ben.cloverserver.response.friend;

import com.ben.cloverserver.response.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFriendResponse extends AbstractResponse {

    List<FriendResponse> friends;
}
