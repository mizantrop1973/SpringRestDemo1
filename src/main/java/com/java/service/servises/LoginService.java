package com.java.service.servises;

import com.java.service.forms.LoginForm;
import com.java.service.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);

}
