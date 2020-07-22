package com.landvibe.chinstagram;

import com.landvibe.chinstagram.models.User;
import com.landvibe.chinstagram.repositories.UserRepository;
import com.landvibe.chinstagram.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userRepository.findById(request.getHeader("id")).orElseThrow(() -> new IllegalArgumentException("This user is not exist."));

        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        verifyToken(givenToken, user.getToken());

        return true;
    }

    private void verifyToken(String givenToken, String membersToken) {
        if (!givenToken.equals(membersToken)) {
            throw new IllegalArgumentException("Incorrect token.");
        }

        jwtUtil.verifyToken(givenToken);
    }

}
