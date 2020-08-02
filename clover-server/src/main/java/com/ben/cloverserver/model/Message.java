package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("message")
public class Message extends AbstractModel {

    @Field("content")
    private String content;

    @Field("sender_id")
    private String senderId;

    @Field("converstation_id")
    private String conversationId;

    @Field("time")
    private Long time;

    @Field("type")
    private Integer type;

    @Field("status")
    private Integer status;

}
