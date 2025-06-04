package com.hexaware.cars.exception;

public class IncidentNumberNotFoundException extends Exception {

    // Default constructor
    public IncidentNumberNotFoundException() {
        super("Incident number not found.");
    }

    // Constructor with custom message
    public IncidentNumberNotFoundException(String message) {
        super(message);
    }
}
