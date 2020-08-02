package com.ben.cloverserver.controller.user;

import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.controller.AbstractController;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.model.User;
import com.ben.cloverserver.request.user.ForgotPasswordRequest;
import com.ben.cloverserver.response.ResponseEntity;
import com.ben.cloverserver.service.base.UserService;
import com.ben.cloverserver.util.SendMailUtil;
import com.ben.cloverserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordAPI extends AbstractController<ForgotPasswordRequest> {

    @Autowired
    private UserService userService;

    private User user;

    @PostMapping("/forgot_password")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request){
        return handle(request);
    }

    @Override
    protected void valid(ForgotPasswordRequest request) {
        if (!StringUtil.validateString(request.getUserName(), request.getMail())){
            throw new ApplicationException(ResponseCode.WRONG_DATA_FORMAT);
        }
    }

    @Override
    protected void process(ForgotPasswordRequest request) {
        user = userService.getUserByUserNameAndMail(request.getUserName(), request.getMail());
        String newPass = StringUtil.randomString();
        SendMailUtil.send(user.getMail(), newPass);
        user.setPassword(StringUtil.encodeBcrypt(newPass));
        userService.updateEntity(user);
    }

    @Override
    protected String createResponse() {
        return new ResponseEntity(ResponseCode.SUCCESS).toString();
    }
}
