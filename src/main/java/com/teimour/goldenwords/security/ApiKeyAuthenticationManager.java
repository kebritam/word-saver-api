package com.teimour.goldenwords.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
        principal = BCrypt.hashpw(principal, getProperty());

        boolean isValid = keyExists(principal);
        if (!isValid) throw new BadCredentialsException("api key was not found");

        authentication.setAuthenticated(true);
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

    private String getProperty() {
        Properties properties = new Properties();

        try(FileReader fileReader = new FileReader("src\\main\\resources\\application.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty("api.key.salt");
    }

}
