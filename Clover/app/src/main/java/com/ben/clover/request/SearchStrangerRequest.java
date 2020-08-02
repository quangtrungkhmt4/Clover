package com.ben.clover.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchStrangerRequest extends AbstractRequest{
    private String token;
    private Integer page;
    private Integer take;
}
