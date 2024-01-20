package com.forcen.senbank.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserVm {

    private String username;
    private String email;
    private String password;
    private String name;
    private String role;

}
