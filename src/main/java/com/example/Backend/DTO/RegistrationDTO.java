package com.example.Backend.DTO;

public class RegistrationDTO {
    // Member variables 
         
    private String name;

    private String email;
 
    private String password;

    private String phone;
    
    private String role;

    // Member functions

    // Constructors
    
    // No-args constructor (required by JPA)
    public RegistrationDTO(){} 

    // Parameterized constructor
    public RegistrationDTO(String name, String email, String password, String phone, String role) {
        // not setting the userId because value of user_id will be set by DB - Autoincrement
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    
    // Getters and Setters

    /* 
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
