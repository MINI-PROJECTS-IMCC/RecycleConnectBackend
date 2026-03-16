package com.example.Backend.Entities;

import jakarta.persistence.*; // to use annotations like @Id,@Column,e.t.c

@Entity
@Table(name = "user")
public class User {

    // Member variables 
        
    @Id // Primaray key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment , starts from 1
    @Column(name = "user_id")
    private Long userId; 

    @Column(nullable = false) // Username can't be null - Full name of a user
    private String name;

    @Column(nullable = false,unique = true) // email can't be null and should be unique
    private String email;

    @Column(nullable = false) // password can't be null 
    private String password;

    private String phone;

    @Column(nullable = false) // role can't be null
    private String role;

    // Member functions

    // Constructors
    
    // No-args constructor (required by JPA)
    public User(){} 

    // Parameterized constructor
    public User(String name, String email, String password, String phone, String role) {
        // not setting the userId because value of user_id will be set by DB - Autoincrement
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    
    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
