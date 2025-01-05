package com.balbasio.EticaretJavaCase.config;

import com.auth0.jwt.exceptions.JWTDecodeException;

import com.balbasio.EticaretJavaCase.repository.UserRepository;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import com.balbasio.EticaretJavaCase.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                Optional<Long> idOpt = jwtService.validateToken(token);
                if (idOpt.isPresent()) {
                    Long id = idOpt.get();
                    Optional<User> userOptional = userRepository.findById(id);
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        setAuthentication(user, user.getUserRole().toString());
                    }


                }
            } catch (JWTDecodeException e) {
                logger.error("JwtDecode Exception", e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Object principal, String authority) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                principal, null, List.of(new SimpleGrantedAuthority(authority))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
