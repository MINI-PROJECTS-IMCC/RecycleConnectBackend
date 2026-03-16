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

    // Member functions

    // 0 arg constructor
    public RecyclerLocation() {}

    // parameterized constructor
    public RecyclerLocation(Long locationId, User user, String serviceArea) {
        this.locationId = locationId;
        this.user = user;
        this.serviceArea = serviceArea;
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
}




