package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("participant")
public class Participant extends AbstractModel{

    @Field("user_id")
    private String userId;

    @Field("conversation_id")
    private String conversationId;

    @Field("reg_date")
    private Long regDate;

    @Field("nick_name")
    private String nickName;
}
