package com.ben.cloverserver.controller.user;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.request.user.CheckActiveRequest;
import com.ben.cloverserver.response.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckActiveAPI extends AbstractController<CheckActiveRequest> {

    @PostMapping("/active")
    public String checkActive(@RequestBody CheckActiveRequest request){
        return handle(request);
    }

    @Override
    protected void valid(CheckActiveRequest request) {
        String userId = getUserIdByToken(request.getToken());
        if (userId == null){
            throw new ApplicationException(ResponseCode.TOKEN_EXPIRED);
        }
    }

    @Override
    protected void process(CheckActiveRequest request) {

    }

    @Override
    protected String createResponse() {
        return new ResponseEntity(ResponseCode.SUCCESS).toString();
    }
}
