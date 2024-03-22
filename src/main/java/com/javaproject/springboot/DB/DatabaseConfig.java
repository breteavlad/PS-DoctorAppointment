package com.javaproject.springboot.DB;

import com.javaproject.springboot.DB.DatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public DatabaseConnection databaseConnection() {
        return DatabaseConnection.getInstance();
    }
}
