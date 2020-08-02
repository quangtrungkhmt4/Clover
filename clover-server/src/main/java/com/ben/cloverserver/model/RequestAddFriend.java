package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("request_add_friend")
public class RequestAddFriend extends AbstractModel{

    @Field("reg_date")
    private Long regDate;

    @Field("from_id")
    private String fromId;

    @Field("to_id")
    private String toId;

    @Field("status")
    private Integer status;

}
