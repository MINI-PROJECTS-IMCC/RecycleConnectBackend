// parent DTO class extended by classes :- 'UserProfile', 'RecyclerProfile'

package com.example.Backend.DTO;

public abstract class UserProfile {
  // member variables 
    private String username; // i.e email in our case
    private String name; // name of the user
    private String phone;

    // 0 args constructor
    public UserProfile() {}

    //  parameterised constructor
    public UserProfile(String username, String name, String phone) {
        this.username = username;
        this.name = name;
        this.phone = phone;
    }
    
    // Methods 

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
} // class ends here
