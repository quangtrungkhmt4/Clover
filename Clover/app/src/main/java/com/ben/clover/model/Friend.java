package com.ben.clover.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {
    private Integer gender;
    private Integer status;
    private String name;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String friendId;
    private String avatar;
}
