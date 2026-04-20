
package com.example.Backend.DTO;
import com.example.Backend.Entities.User;
import java.util.List;


// First store data in pickup_request table then in item table
public class RequestDTO {

    // User user; //  I need to fetch user from jwt token 
    String recyclerEmail; 
    List<ItemDTO> items;

    // Constructor
    public RequestDTO() {
        
    }

    public RequestDTO(/*User user, */String recyclerEmail, List<ItemDTO> items) {
       // this.user = user;
        this.recyclerEmail = recyclerEmail;
        this.items = items;
    }
    
    // Getters and setters 
    /* 
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    */
    public String getRecyclerEmail() {
        return recyclerEmail;
    }
    public void setRecyclerEmail(String recyclerEmail) {
        this.recyclerEmail = recyclerEmail;
    }
    public List<ItemDTO> getItems(){
        return items; // returns the reference of the array of type 'ItemDTO'
    }

}
