package com.forcen.senbank.web.rest;

import com.forcen.senbank.service.AuthService;
import com.forcen.senbank.web.rest.vm.JWTAuthResponse;
import com.forcen.senbank.web.rest.vm.LoginVm;
import com.forcen.senbank.web.rest.vm.RegisterUserVm;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginVm loginVm){
        log.debug("REST request to authenticate user {}", loginVm.getUsernameOrEmail());
        String token = authService.login(loginVm);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserVm registerUserVm){
        authService.register(registerUserVm);
         ResponseEntity.ok("User registered successfully");
         return ResponseEntity.ok().build();
    }

}
