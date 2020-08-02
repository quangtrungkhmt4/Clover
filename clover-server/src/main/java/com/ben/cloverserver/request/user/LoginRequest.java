package com.ben.cloverserver.request.user;

import com.ben.cloverserver.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest extends BaseRequest {
    private String userName;
    private String password;
}
