package com.ben.cloverserver.request.user;

import com.ben.cloverserver.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest extends BaseRequest {
    private String userName;
    private String name;
    private String password;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String mail;
    private Integer gender;
}
