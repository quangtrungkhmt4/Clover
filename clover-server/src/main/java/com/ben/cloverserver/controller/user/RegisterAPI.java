package com.ben.cloverserver.controller.user;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.constant.UserStatus;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.request.user.RegisterRequest;
import com.ben.cloverserver.response.ResponseEntity;
import com.ben.cloverserver.response.user.RegisterResponse;
import com.ben.cloverserver.service.base.UserService;
import com.ben.cloverserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterAPI extends AbstractController<RegisterRequest> {

    @Autowired
    private UserService userService;

    private User newUser;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        return handle(registerRequest);
    }

    @Override
    protected void valid(RegisterRequest request) {
        if (!StringUtil.validateString(request.getUserName(), request.getPassword(), request.getMail())){
            throw new ApplicationException(ResponseCode.WRONG_DATA_FORMAT);
        }

        if (!StringUtil.isEmailValid(request.getMail())){
            throw new ApplicationException(ResponseCode.MAIL_INVALID);
        }
    }

    @Override
    protected void process(RegisterRequest request) {
        if (userService.isExistsUser(request.getUserName())){
            throw new ApplicationException(ResponseCode.USER_NAME_IS_EXISTS);
        }
        if (userService.isExistsMail(request.getMail())){
            throw new ApplicationException(ResponseCode.MAIL_IS_EXISTS);
        }
        newUser = register(request);
        newUser.setUserId(newUser.getId());
        userService.updateEntity(newUser);
    }

    @Override
    protected String createResponse() {
        RegisterResponse registerResponse = RegisterResponse.builder()
                .userName(newUser.getUserName())
                .dayOfBirth(newUser.getDayOfBirth())
                .monthOfBirth(newUser.getMonthOfBirth())
                .yearOfBirth(newUser.getYearOfBirth())
                .mail(newUser.getMail())
                .name(newUser.getName())
                .status(newUser.getStatus())
                .gender(newUser.getGender())
                .token(generateToken(newUser.getId())).build();
        return new ResponseEntity(registerResponse).toString();
    }

    private User register(RegisterRequest request){
        User user = User.builder()
                .userName(request.getUserName())
                .dayOfBirth(request.getDayOfBirth())
                .monthOfBirth(request.getMonthOfBirth())
                .yearOfBirth(request.getYearOfBirth())
                .gender(request.getGender())
                .name(request.getName())
                .regDate(System.currentTimeMillis())
                .status(UserStatus.ONLINE)
                .mail(request.getMail())
                .avatar("default_avatar.png")
                .password(StringUtil.encodeBcrypt(request.getPassword())).build();
        return userService.insertEntity(user);
    }
}
