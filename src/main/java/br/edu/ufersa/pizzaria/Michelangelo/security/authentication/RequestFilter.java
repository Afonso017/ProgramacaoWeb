package br.edu.ufersa.pizzaria.Michelangelo.security.authentication;

import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.User;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.UserRepository;
import br.edu.ufersa.pizzaria.Michelangelo.security.userdetails.UserDetailsImpl;
import br.edu.ufersa.pizzaria.Michelangelo.security.userdetails.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import br.edu.ufersa.pizzaria.Michelangelo.security.config.SecurityConfig;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                String subject = jwtService.extractSubject(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

                Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("O token está ausente.");
                throw new RuntimeException("O token está ausente.");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfig.NOT_REQUIRED).contains(requestURI);
    }

}