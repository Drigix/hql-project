package com.hql.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionConfig {

    private String url;
    private String username;
    private String password;

    public DbConnectionConfig(String propertiesFile) {
        loadProperties(propertiesFile);
    }

    private void loadProperties(String propertiesFile) {
        // Wczytaj właściwości z pliku (np. application.properties lub application.yml)
        Properties properties = new Properties();
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (inputStream != null) {
                properties.load(inputStream);
                this.url = properties.getProperty("db.url");
                this.username = properties.getProperty("db.username");
                this.password = properties.getProperty("db.password");
            } else {
                throw new RuntimeException("Properties file not found: " + propertiesFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
