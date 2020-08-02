package com.ben.cloverserver.entity;

import com.ben.cloverserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFriend {

    @Field("user_id")
    private String userId;

    @Field("friend_id")
    private String friendId;

    @Field("reg_date")
    private Long regDate;

    @Field("send_request_id")
    private String sendRequestId;

    @Field("info")
    private List<User> info;

}
