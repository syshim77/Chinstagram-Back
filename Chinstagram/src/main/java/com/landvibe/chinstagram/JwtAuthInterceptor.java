package com.landvibe.chinstagram;

import com.landvibe.chinstagram.models.User;
import com.landvibe.chinstagram.repositories.UserRepository;
import com.landvibe.chinstagram.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
        User user = userRepository.findById(request.getHeader("id")).orElseThrow(() -> new AuthenticationException("This user is not exist."));

        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        verifyToken(givenToken, user.getToken());

        return true;
    }

    private void verifyToken(String givenToken, String membersToken) throws AuthenticationException {
        if (!givenToken.equals(membersToken)) {
            throw new AuthenticationException("Incorrect token.");
        }

        jwtUtil.verifyToken(givenToken);
    }

}
