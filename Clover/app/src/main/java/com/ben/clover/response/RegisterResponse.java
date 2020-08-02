package com.ben.clover.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse extends AbstractResponse {

    private String userName;
    private String name;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String mail;
    private Integer status;
    private String token;
    private Integer gender;

}
