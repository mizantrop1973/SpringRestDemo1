package com.java.service.servises;

import com.java.service.forms.LoginForm;
import com.java.service.models.Token;
import com.java.service.models.User;
import com.java.service.repositories.TokensRepository;
import com.java.service.repositories.UsersRepository;
import com.java.service.transfer.TokenDto;
import com.mysql.cj.xdevapi.Table;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.java.service.transfer.TokenDto.*;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TokensRepository tokensRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = usersRepository.findOneByLogin(loginForm.getLogin());
        if(userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                   .user(user)
                   .value(RandomStringUtils.random(10, true, true))
                   .build();
                tokensRepository.save(token);
                return from(token);
            }
        } throw new IllegalArgumentException("User not found");
    }
}
