package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("friend")
public class Friend extends AbstractModel{

    @Field("user_id")
    private String userId;

    @Field("friend_id")
    private String friendId;

    @Field("reg_date")
    private Long regDate;

    @Field("send_request_id")
    private String sendRequestId;

}
