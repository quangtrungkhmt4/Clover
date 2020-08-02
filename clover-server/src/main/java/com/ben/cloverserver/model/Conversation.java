package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("conversation")
public class Conversation extends AbstractModel{

    @Field("title")
    private String title;

    @Field("reg_date")
    private Long regDate;
}
