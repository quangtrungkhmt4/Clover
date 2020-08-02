package com.ben.clover.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest extends AbstractRequest {
    private String userName;
    private String password;
}
