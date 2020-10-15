package com.bsuir.its.security.jwt;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.security.jwt.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    private BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    protected void init() {
        secret = Base64.
                getEncoder().
                encodeToString(secret.getBytes());
    }

    public String createToken(String username, List<Role> roles){

        Claims claims = Jwts.claims().setSubject(username);
        List<String> rolesJWT = getRoleNames(roles);
        claims.put("roles",rolesJWT);

        Date now = new Date();

        Date validity = new Date(now.getTime()  + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Authentication getAuthentication(String token){

        String email = getEmail(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return new UsernamePasswordAuthenticationToken(userDetails,"",authorities);
    }

    public String getEmail(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer_")){
            String token = bearerToken.split("_")[1];
            return token;
        }

        return null;
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            String body = claims.getBody().toString();

            return !claims
                    .getBody()
                    .getExpiration()
                    .before(new Date());

        }catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }

    }

    private List<String> getRoleNames(List<Role> userRoles){
        List<String> result = new ArrayList<>();

        for (Role r : userRoles) {
            String name = r.getName();
            result.add(name);
        }

        return result;
    }
}
