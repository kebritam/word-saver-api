package com.teimour.wordsapi.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kebritam
 * Project words-api
 * Created on 18/04/2021
 */

public class ApiKeyFilter extends OncePerRequestFilter {

    private final KeyManager keyManager;
    public static final String headerName = "X-API-KEY";

    public ApiKeyFilter(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(headerName);

        if (header == null || header.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (keyManager.keyIsValid(header)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    header,
                    null,
                    AuthorityUtils.NO_AUTHORITIES
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
