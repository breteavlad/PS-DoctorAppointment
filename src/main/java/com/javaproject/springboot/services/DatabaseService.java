package com.javaproject.springboot.services;


import com.javaproject.springboot.DB.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final DatabaseConnection databaseConnection;

    @Autowired
    public DatabaseService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void doSomethingWithDatabase() {
        // Use the databaseConnection instance to perform database operations
        // For example:
        // Connection connection = databaseConnection.getConnection();
        // Perform operations using connection
    }
}