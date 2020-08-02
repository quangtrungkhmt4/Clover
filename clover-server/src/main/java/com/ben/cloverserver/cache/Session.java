package com.ben.cloverserver.cache;

import com.ben.cloverserver.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private String userId;
    private String token;

    public Session(String userId){
        this.userId = userId;
        token = StringUtil.randomString();
    }
}
