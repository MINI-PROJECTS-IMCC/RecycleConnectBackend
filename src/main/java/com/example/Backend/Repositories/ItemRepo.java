package com.example.Backend.Repositories;

import com.example.Backend.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository; // interface 'JpaRepository' is defined here 
import org.springframework.data.jpa.repository.Query; // to use @Query
import org.springframework.data.repository.query.Param; // to use @Param


import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
    /* 
    @Query("SELECT u FROM User u WHERE u.email = :email") 
    Optional<User> getUserByEmail(@Param("email") String email);
    */
   // returns Recycler object - if user founda
   // returns null - if user doesn't exists
   

}
