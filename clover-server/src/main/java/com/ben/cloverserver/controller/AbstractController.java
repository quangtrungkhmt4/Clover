package com.ben.cloverserver.controller;

import com.ben.cloverserver.cache.Session;
import com.ben.cloverserver.cache.SessionManager;
import com.ben.cloverserver.constant.ResponseCode;
import com.ben.cloverserver.exception.ApplicationException;
import com.ben.cloverserver.request.BaseRequest;
import com.ben.cloverserver.response.ResponseEntity;

public abstract class AbstractController<R extends BaseRequest> {

    protected String handle(R request){
        try {
            valid(request);
            process(request);
            return createResponse();
        }catch (ApplicationException e){
            return new ResponseEntity(e.getCode()).toString();
        }catch (Exception e){
            return new ResponseEntity(ResponseCode.UNKNOWN_ERROR).toString();
        }
    }

    protected abstract void valid(R request);

    protected abstract void process(R request);

    protected abstract String createResponse();

    protected String getUserIdByToken(String token){
        Session session = SessionManager.getSession(token);
        if (session == null){
            throw new ApplicationException(ResponseCode.TOKEN_EXPIRED);
        }
        return session.getUserId();
    }

    protected String generateToken(String userId){
        return SessionManager.putSession(new Session(userId));
    }

}
