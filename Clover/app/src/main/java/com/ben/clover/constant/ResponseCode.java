package com.ben.clover.constant;

import android.content.Context;

import com.ben.clover.R;

public class ResponseCode {
    public static final int SUCCESS = 0;
    public static final int UNKNOWN_ERROR = 1;
    public static final int WRONG_DATA_FORMAT = 2;
    public static final int USER_NAME_IS_EXISTS = 3;
    public static final int MAIL_INVALID = 4;
    public static final int TOKEN_EXPIRED = 5;
    public static final int MAIL_IS_EXISTS = 6;
    public static final int WRONG_USERNAME_OR_PASS = 7;

    public static String getMessage(Context context, int code){
        switch (code){
            case UNKNOWN_ERROR:
                return context.getString(R.string.unknownError);
            case WRONG_DATA_FORMAT:
                return context.getString(R.string.wrongDataFormat);
            case USER_NAME_IS_EXISTS:
                return context.getString(R.string.userNameIsExists);
            case TOKEN_EXPIRED:
                return context.getString(R.string.tokenExpired);
            case MAIL_IS_EXISTS:
                return context.getString(R.string.mailIsExists);
            case WRONG_USERNAME_OR_PASS:
                return context.getString(R.string.wrongUserOrPass);
            default:
                return context.getString(R.string.unknownError);
        }
    }
}
