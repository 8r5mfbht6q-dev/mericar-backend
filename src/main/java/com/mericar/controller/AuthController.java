package com.mericar.controller;

import com.mericar.dto.LoginRequest;
import com.mericar.dto.LoginResponse;
import com.mericar.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ){
        return authService.login(request);
    }

}