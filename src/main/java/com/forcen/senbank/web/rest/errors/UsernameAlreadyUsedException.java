package com.forcen.senbank.web.rest.errors;

public class UsernameAlreadyUsedException extends BadRequestAlertException{
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super(ErrorConstants.USERNAME_ALREADY_USED_TYPE, "Username is already in use!", "userManagement", "usernameexists");
    }
}
