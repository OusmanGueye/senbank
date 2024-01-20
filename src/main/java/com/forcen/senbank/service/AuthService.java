package com.forcen.senbank.service;

import com.forcen.senbank.domain.User;
import com.forcen.senbank.web.rest.vm.LoginVm;
import com.forcen.senbank.web.rest.vm.RegisterUserVm;

public interface AuthService {

    /**
     * Login user
     * @return JWT token
     */
    String login(LoginVm loginVm);

    /**
     * Register RegisterUserVm
     * @return User
     */
    User register(RegisterUserVm registerUserVm);
}
