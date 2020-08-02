package com.ben.cloverserver.request.friend;

import com.ben.cloverserver.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFriendRequest extends BaseRequest {
    private String token;
    private int skip;
    private int take;
}
