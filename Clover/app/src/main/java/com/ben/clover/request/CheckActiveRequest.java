package com.ben.clover.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckActiveRequest extends AbstractRequest{
    private String token;
}
