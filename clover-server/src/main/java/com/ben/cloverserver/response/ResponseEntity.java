package com.ben.cloverserver.response;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseEntity {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AbstractResponse data;

    public ResponseEntity() {

    }

    public ResponseEntity(Integer code) {
        this.code = code;
    }

    public ResponseEntity(AbstractResponse data) {
        this.code = ResponseCode.SUCCESS;
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.castFromObject(this);
    }
}
