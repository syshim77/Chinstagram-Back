package com.landvibe.chinstagram.jwt;

import com.landvibe.chinstagram.models.LogInRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${cloud.aws.credentials.secretKey}")
    private final String SECRET_KEY;

    public String createToken(String key, LogInRequest logInRequest) throws Exception {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(logInRequest.getId())
                .claim(key, logInRequest)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
    }

    private byte[] generateKey() throws Exception {
        byte[] key = null;

        try {
            key = SECRET_KEY.getBytes("UTF-8");
        } catch(UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("Unsupported Encoding Exception while generating key.");
        }

        return key;
    }

    public boolean isUsable(String jwt) throws Exception {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);

            return true;
        } catch(Exception e) {
            throw new AuthenticationException("Token validation error.");
        }
    }

    public Map<String, Object> get(String key) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch(Exception e) {
            throw new AuthenticationException("Getting data from JWT error.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);

        return value;
    }

    @Cacheable(value = "userInfo", key = "#logInRequest")
    public void cacheUser(LogInRequest logInRequest) {
        log.info("User ID: " + logInRequest.getId() + " is cached.");
    }
}
