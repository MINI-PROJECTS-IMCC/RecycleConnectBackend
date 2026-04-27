package com.example.Backend.DTO;

public class RecyclerProfile extends UserProfile{
   // Member variables
    private String serviceArea;
    private String city;

    // Methods 
    
    // 0 args constructor
    public RecyclerProfile(){}

    // parameterised constructor
    public RecyclerProfile(String username, String name, String phone,String serviceArea,String city) {
        super(username, name, phone);
        this.serviceArea = serviceArea;
        this.city = city; 
    }

    // getters and setters
    public String getServiceArea() {
        return serviceArea;
    }
    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
}
