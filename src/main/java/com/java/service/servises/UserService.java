package com.java.service.servises;

import com.java.service.forms.UserForm;
import com.java.service.models.User;

import java.util.List;


public interface UserService {
    void signUp(UserForm userForm);
    List<User> findAll();
    User findOne(Long id);
}
