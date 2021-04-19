package com.teimour.wordsapi.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author kebritam
 * Project words-api
 * Created on 18/04/2021
 */

public class ApiKeyFilter extends OncePerRequestFilter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    public static final String headerName = "X-API-KEY";
    private static final Stack<String> apiKeys = new Stack<>();

    public ApiKeyFilter(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
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

        if (keyExistsInCache(header) || keyExistsInDatabase(header)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    header,
                    null,
                    AuthorityUtils.NO_AUTHORITIES
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean keyExistsInCache(String supposedKey) {
        return apiKeys.stream()
                .anyMatch(key -> passwordEncoder.matches(supposedKey, key));
    }

    private boolean keyExistsInDatabase(String supposedKey) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT key FROM api_authorization")) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String key = result.getString("key");
                if (passwordEncoder.matches(supposedKey, key)) {
                    return true;
                } else {
                    apiKeys.push(key);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
