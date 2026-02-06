package com.udemyCourse.course.controllers;

import com.udemyCourse.course.dataDTO.Security.AccountCredentialsDTO;
import com.udemyCourse.course.dataDTO.Security.TokenDTO;
import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint!")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(summary = "Authenticate in user and returns a token")
    @PostMapping("/signin")

    public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO credentialsDTO){
        System.out.println("Requisicao recebida!");
        if(credentialsIsInvalid(credentialsDTO))
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                "Invalid Client Request!"
        );
        var token = authService.signin(credentialsDTO);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                "Invalid Client Request!"
        );
        return ResponseEntity.ok().body(token);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDTO credentialsDTO) {
        return credentialsDTO == null && StringUtils.isBlank(credentialsDTO.getPassword())
                || StringUtils.isBlank(credentialsDTO.getUsername());
    }


    @Operation(summary = "Refresh token for autenticated user and return a tokens")
    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refresh(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken){
        System.out.println("Requisicao recebida!");
        if(parametersAreInvalid(username,refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    "Invalid Client Request!"
            );
        var token = authService.refreshToken(username,refreshToken);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                "Invalid Client Request!"
        );
        return ResponseEntity.ok().body(token);
    }

    private boolean parametersAreInvalid(String username, String refreshToken) {
        return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
    }

    @PostMapping(value = "/createUser")
    public AccountCredentialsDTO create(@RequestBody AccountCredentialsDTO accountCredentialsDTO){
        return authService.create(accountCredentialsDTO);
    }

}
