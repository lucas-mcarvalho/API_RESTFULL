package com.udemyCourse.course.services;

import com.udemyCourse.course.dataDTO.Security.AccountCredentialsDTO;
import com.udemyCourse.course.dataDTO.Security.TokenDTO;
import com.udemyCourse.course.repository.UserRepository;
import com.udemyCourse.course.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenDTO> signin(AccountCredentialsDTO credentialsDTO){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername()
                    ,credentialsDTO.getPassword()));
            var user = repository.findByUsername(credentialsDTO.getUsername());
        if(user ==null){
            throw new UsernameNotFoundException
                    ("Username "+credentialsDTO.getUsername()+ "not found!");

        }
        var token = tokenProvider.createAccesToken(credentialsDTO.getUsername(),
                user.getRoles());
            return ResponseEntity.ok(token);
    }
}
