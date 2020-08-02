package com.ben.clover.response;

import com.ben.clover.model.Friend;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchStrangerResponse extends AbstractResponse{
    List<Friend> strangers;
    private int total;
}
