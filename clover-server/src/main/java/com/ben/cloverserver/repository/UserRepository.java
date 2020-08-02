package com.ben.cloverserver.repository;

import com.ben.cloverserver.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsUserByUserName(String userName);

    boolean existsUserByMail(String mail);

    User findUserByUserName(String userName);

    User findUserByUserNameAndMail(String userName, String mail);

    List<User> findAllByUserIdIsNotIn(List<String> friendIds, Pageable pageable);

    Integer countAllByUserIdIsNotIn(List<String> friendIds);

}
