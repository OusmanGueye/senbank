package com.forcen.senbank.web.rest;

import com.forcen.senbank.service.AuthService;
import com.forcen.senbank.web.rest.vm.JWTAuthResponse;
import com.forcen.senbank.web.rest.vm.LoginVm;
import com.forcen.senbank.web.rest.vm.RegisterUserVm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginVm loginVm){
        String token = authService.login(loginVm);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserVm registerUserVm){
        authService.register(registerUserVm);
        return ResponseEntity.ok("User registered successfully");
    }

}
