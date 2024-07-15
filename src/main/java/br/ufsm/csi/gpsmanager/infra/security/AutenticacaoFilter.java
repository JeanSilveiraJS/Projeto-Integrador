package br.ufsm.csi.gpsmanager.infra.security;

import br.ufsm.csi.gpsmanager.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {
    private final TokenServiceJWT tokenServiceJWT;
    private final AutenticacaoService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = request.getHeader(AUTHORIZATION);

        if (tokenJWT != null && tokenJWT.startsWith("Bearer ")) {
            tokenJWT = tokenJWT.replace("Bearer ", "").trim();
            String subject = tokenServiceJWT.getSubject(tokenJWT);
            UserDetails userDetails = service.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.setHeader(AUTHORIZATION, "Bearer " + tokenJWT);
        }

        filterChain.doFilter(request, response);
    }
}