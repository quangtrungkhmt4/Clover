package com.ben.cloverserver.request.user;

import com.ben.cloverserver.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequest extends BaseRequest {
    private String userName;
    private String mail;
}