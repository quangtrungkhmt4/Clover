package com.ben.cloverserver.service.base;

import com.ben.cloverserver.model.User;

import java.util.List;

public interface UserService extends Service<User> {

    boolean isExistsUser(String userName);

    boolean isExistsMail(String mail);

    User getUserByUserName(String userName);

    User getUserByUserNameAndMail(String userName, String mail);

    List<User> getStranger(List<String> friendIds, int page, int take);

    Integer getTotalStranger(List<String> friends);

}
