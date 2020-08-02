package com.ben.clover.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T extends AbstractResponse> {

    private int code;
    private T data;
}
