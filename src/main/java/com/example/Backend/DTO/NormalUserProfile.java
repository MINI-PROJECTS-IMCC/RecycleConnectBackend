package com.example.Backend.DTO;

public class NormalUserProfile extends UserProfile{
   
    // Member variables
    private String address;
    private String city;

    // Methods 
    
    // 0 args constructor
    public NormalUserProfile(){}

    // parameterised constructor
    public NormalUserProfile(String username, String name, String phone,String address,String city) {
        super(username, name, phone);
        this.address = address;
        this.city = city; 
    }

    // getters and setters
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    
} // class ends here
