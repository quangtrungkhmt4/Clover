package com.ben.cloverserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
@Builder
public class User extends AbstractModel {

    @Field("user_name")
    private String userName;

    @Field("password")
    private String password;

    @Field("gender")
    private Integer gender;

    @Field("avatar")
    private String avatar;

    @Field("status")
    private Integer status;

    @Field("reg_date")
    private Long regDate;

    @Field("name")
    private String name;

    @Field("day_of_birth")
    private String dayOfBirth;

    @Field("month_of_birth")
    private String monthOfBirth;

    @Field("year_of_birth")
    private String yearOfBirth;

    @Field("mail")
    private String mail;

    @Field("user_id")
    private String userId;


}
