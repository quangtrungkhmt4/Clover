package com.ben.clover.response;

import com.ben.clover.model.Friend;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFriendResponse extends AbstractResponse{
    List<Friend> friends;
}
