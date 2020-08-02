package com.landvibe.chinstagram.jwt;

import com.landvibe.chinstagram.custom.CheckJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    private String HEADER_TOKEN_KEY = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String givenToken = request.getHeader(HEADER_TOKEN_KEY);

        HandlerMethod method = (HandlerMethod)handler;
        CheckJwt checkJwt = method.getMethodAnnotation(CheckJwt.class);

        if (checkJwt == null || !checkJwt.needCheck()) {
            return true;
        }

        if (givenToken == null || !jwtService.isUsable(givenToken)) {
            throw new AuthenticationException("Token validation error.");
        }

        return true;
    }
}
