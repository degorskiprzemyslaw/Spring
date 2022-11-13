package com.sdacademy.springdatajpaexample.web.mvc.mappers;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.web.mvc.form.CreateUserForm;

public class UserMapper {
    public static User toEntity(CreateUserForm form){
        return new User(form.getFirstName(), form.getLastName(), form.getLogin(), form.getPassword());
    }


}
