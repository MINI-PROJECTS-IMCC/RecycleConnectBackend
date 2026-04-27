package com.example.Backend.Entities;
import jakarta.persistence.*; // to use annotations like @Id,@Column,e.t.c

@Entity
@Table(name = "recycler_location")
public class RecyclerLocation {

    // Member variables 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment , starts from 1
    @Column(name = "location_id")
    private Long locationId;
    
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    
    @Column(name = "service_area")
    private String serviceArea;

    @Column(name = "service_area")
    private String city;

    // Member functions

    // 0 arg constructor
    public RecyclerLocation() {}

    // parameterized constructor
    public RecyclerLocation(Long locationId, User user, String serviceArea, String city) {
        this.locationId = locationId;
        this.user = user;
        this.serviceArea = serviceArea;
        this.city = city;
    }

    // Getters and setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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




