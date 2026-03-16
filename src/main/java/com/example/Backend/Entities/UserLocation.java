package com.example.Backend.Entities;
import jakarta.persistence.*; // to use annotations like @Id,@Column,e.t.c
import com.example.Backend.Entities.User; // to use class 'User'

@Entity
@Table(name = "user_location")
public class UserLocation {

    // Member variables 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment , starts from 1
    @Column(name = "location_id")
    private Long locationId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String address;

    private String city;

    // Member functions

    // 0 arg constructor
    public UserLocation() {}

    // parameterized constructor
    public UserLocation(Long locationId, User user, String address, String city) {
        this.locationId = locationId;
        this.user = user;
        this.address = address;
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

    

}
