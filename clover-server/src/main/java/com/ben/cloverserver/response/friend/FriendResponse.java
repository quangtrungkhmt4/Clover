package com.ben.cloverserver.response.friend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendResponse {
    private Integer gender;
    private Integer status;
    private String name;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String friendId;
    private String avatar;

}
