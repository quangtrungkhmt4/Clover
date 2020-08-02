package com.ben.clover.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchFriendRequest extends AbstractRequest{
    private String token;
    private Integer skip;
    private Integer take;
}
