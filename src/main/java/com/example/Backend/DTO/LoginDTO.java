package com.example.Backend.DTO;

public class LoginDTO {

    // Member variables
    private String email;
    private String password;
    private String role;
    // Member functions
  
  // 0 args constructor 
  public LoginDTO() {
  }

  // Parameterized cosnstructor
  public LoginDTO(String email, String password, String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  // Getters and setters
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
}
