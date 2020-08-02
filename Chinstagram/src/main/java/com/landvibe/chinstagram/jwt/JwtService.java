package com.landvibe.chinstagram.jwt;

import com.landvibe.chinstagram.models.LogInRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "SECRET_KEY";
    private byte[] JwtKey;

    @PostConstruct
    private void getSecretKey() throws Exception {
        JwtKey = SECRET_KEY.getBytes("UTF-8");
    }

    public String createToken(String key, LogInRequest logInRequest) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(logInRequest.getId())
                .claim(key, logInRequest)
                .signWith(SignatureAlgorithm.HS256, JwtKey)
                .compact();
    }

    public boolean isUsable(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(JwtKey)
                    .parseClaimsJws(jwt);

            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(JwtKey)
                    .parseClaimsJws(jwt);
        } catch(Exception e) {
            Map<String, Object> value = null;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);

        return value;
    }
}