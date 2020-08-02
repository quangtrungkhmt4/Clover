package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("unread_message")
public class UnreadMessage extends AbstractModel{

    @Field("message_id")
    private String messageId;

    @Field("conversation_id")
    private String conversationId;

    @Field("sender_id")
    private String senderId;
}
