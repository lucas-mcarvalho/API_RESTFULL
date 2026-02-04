package com.udemyCourse.course.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctc.wstx.util.StringUtil;
import com.udemyCourse.course.dataDTO.Security.TokenDTO;
import com.udemyCourse.course.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long expireLenght = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected  void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDTO createAccesToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime()+expireLenght);
        String accesToken = getAccesToken(username,roles,now,validity);
        String refreshToken = getRefreshToken(username,roles,now);
        return new TokenDTO(username,accesToken,refreshToken,true,now,validity);
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date refreshTokenvalidity = new Date(now.getTime()+ expireLenght *3);
        return JWT.create().withClaim("roles",roles)
                .withIssuedAt(now).withExpiresAt(refreshTokenvalidity).withSubject(username)
               .sign(algorithm);
    }

    private String getAccesToken(String username, List<String> roles, Date now, Date validity) {
        String issueURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create().withClaim("roles",roles)
                .withIssuedAt(now).withExpiresAt(validity).withSubject(username)
                .withIssuer(issueURL).sign(algorithm);
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm algorithm1 = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algorithm1).build();
        DecodedJWT decodedJWT  = verifier.verify(token);
        return decodedJWT;
    }

    public String ResolveToken(HttpServletRequest request){
        String betterToken = request.getHeader("Authorization");
        if(betterToken!= null && betterToken.startsWith("Bearer ")){
            return betterToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedToken = decodedToken(token);
      try {
          if (decodedToken.getExpiresAt().before(new Date())) {
              return false;
          }else {
              return true;
          }
          }catch (Exception e){
          throw  new InvalidJwtAuthenticationException("Expired or invalid JWT token");
      }

    }


}
