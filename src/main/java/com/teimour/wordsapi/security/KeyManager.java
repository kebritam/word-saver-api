// SPDX-License-Identifier: MIT

package com.teimour.wordsapi.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author kebritam
 * Project words-api
 */

public class KeyManager {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    public KeyManager(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    private static final Stack<String> apiKeys = new Stack<>();

    public boolean keyIsValid(String supposedKey) {
        return keyExistsInCache(supposedKey) || keyExistsInDatabase(supposedKey);
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
