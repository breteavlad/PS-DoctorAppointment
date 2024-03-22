package com.javaproject.springboot.controller;

import com.javaproject.springboot.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DatabaseService databaseService;

    @Autowired
    public Controller(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/database")
    public String performDatabaseOperation() {
        // Call the method in DatabaseService to perform database operations
        databaseService.doSomethingWithDatabase();
        return "Database operation performed!";
    }
}
