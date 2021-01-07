package com.teimour.goldenwords.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kebritam
 * Project golden-words
 * Created on 04/01/2021
 */

public class ApiKeyAuthenticationManager implements AuthenticationManager {

    private final DataSource dataSource;

    public ApiKeyAuthenticationManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        boolean isValid = keyExists(principal);
        authentication.setAuthenticated(isValid);

        return authentication;
    }

    private boolean keyExists(String principal) {
        boolean isValid = false;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT EXISTS (SELECT key FROM api_authorization WHERE key=?)")) {

            statement.setString(1, principal);
            ResultSet result = statement.executeQuery();
            result.next();
            isValid = result.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

}
