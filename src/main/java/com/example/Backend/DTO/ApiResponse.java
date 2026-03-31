package com.example.Backend.DTO;

public class ApiResponse {

    // Member variables
    private String message;
    private String token;

    // Member functions :-

    // 0 arg Constructor
    public ApiResponse() {
        token = null;
    }
    // Parameterized constructor
    public ApiResponse(String message,String token) {
        this.message = message;
        this.token = token;
    }

    // Getters and setters
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }  
}
