package com.udemyCourse.course.services;

import com.udemyCourse.course.dataDTO.Security.AccountCredentialsDTO;
import com.udemyCourse.course.dataDTO.Security.TokenDTO;
import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.exceptions.RequireObjectsIsNullException;
import com.udemyCourse.course.model.Person;
import com.udemyCourse.course.model.User;
import com.udemyCourse.course.repository.UserRepository;
import com.udemyCourse.course.security.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.udemyCourse.course.mapper.ObjectMapper.parserObject;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(AuthService.class);

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
        var token = tokenProvider.createAccessToken(credentialsDTO.getUsername(),
                user.getRoles());
            return ResponseEntity.ok(token);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username,String refreshToken){
        var user = repository.findByUsername(username);
        TokenDTO token;
        if(user !=null) {
            token = tokenProvider.refreshToken(refreshToken);
        }
        else {
            throw new UsernameNotFoundException
                    ("Username " + username + "not found!");
        }

        return ResponseEntity.ok(token);

        }


    private String generateHashedPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "",8,185000, Pbkdf2PasswordEncoder.
                SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        encoders.put("pbkdf2",pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2",encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
            return  passwordEncoder.encode(password);

    }

    public AccountCredentialsDTO create(AccountCredentialsDTO user){
        if(user == null) throw new RequireObjectsIsNullException();
        logger.info("Creating one User!");
        var entity = new User();
        entity.setFullname(user.getFullname());
        entity.setUsername(user.getUsername());
        entity.setPassword(generateHashedPassword(user.getPassword()));
        entity.setAccount_non_expired(true);
        entity.setAccount_non_locked(true);
        entity.setCredentials_non_expired(true);
        entity.setEnabled(true);
        return  parserObject(repository.save(entity),AccountCredentialsDTO.class);
    }
}

