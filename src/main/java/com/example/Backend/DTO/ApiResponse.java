package com.example.Backend.DTO;

public class ApiResponse {

    // Member variables
    private String msg;

    // Member functions :-

    // 0 arg Constructor
    public ApiResponse() {
        
    }
    // Parameterized constructor
    public ApiResponse(String msg) {
        this.msg = msg;
    }

    // Getters and setters
    public void setMessage(String msg) {
        this.msg = msg;
    }
    public String getMessage() {
        return msg;
    }

}
