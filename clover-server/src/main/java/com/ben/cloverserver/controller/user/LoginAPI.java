package com.ben.cloverserver.controller.user;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.request.user.LoginRequest;
import com.ben.cloverserver.response.ResponseEntity;
import com.ben.cloverserver.response.user.RegisterResponse;
import com.ben.cloverserver.service.base.UserService;
import com.ben.cloverserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAPI extends AbstractController<LoginRequest> {

    @Autowired
    private UserService userService;

    private User user;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return handle(request);
    }

    @Override
    protected void valid(LoginRequest request) {
        if (!StringUtil.validateString(request.getUserName(), request.getPassword())){
            throw new ApplicationException(ResponseCode.WRONG_DATA_FORMAT);
        }
    }

    @Override
    protected void process(LoginRequest request) {
        user = userService.getUserByUserName(request.getUserName());

        if (!StringUtil.checkPass(request.getPassword(), user.getPassword())){
            throw new ApplicationException(ResponseCode.WRONG_USERNAME_OR_PASS);
        }
    }

    @Override
    protected String createResponse() {
        RegisterResponse registerResponse = RegisterResponse.builder()
                .userName(user.getUserName())
                .dayOfBirth(user.getDayOfBirth())
                .monthOfBirth(user.getMonthOfBirth())
                .yearOfBirth(user.getYearOfBirth())
                .mail(user.getMail())
                .name(user.getName())
                .status(user.getStatus())
                .gender(user.getGender())
                .token(generateToken(user.getId())).build();
        return new ResponseEntity(registerResponse).toString();
    }
}
