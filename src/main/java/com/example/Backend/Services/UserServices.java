package com.example.Backend.Services;

import java.util.List;
import com.example.Backend.Entities.*; // to use class 'User' and other entities
// import com.example.Backend.Repositories.RecyclerRepo;
// To use interface UserRepo
import com.example.Backend.Repositories.*; //  To use multiple repositories repositories

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Backend.DTO.ApiResponse; // to use class 'ApiResponse'
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // to use class 'BCryptPasswordEncoder'
import com.example.Backend.Util.JwtUtil;
import com.example.Backend.DTO.RequestDTO;
import com.example.Backend.DTO.ItemDTO;



@Service 
public class UserServices {

    @Autowired
    UserRepo ur; // ur used to perform operations on 'user'table 

    @Autowired
    RecyclerRepo rr; // rr used to perform operations on 'recycler' table 

    @Autowired
    PickupRequestRepo prr; // prr used to perform operations on 'pickup_request' table

    @Autowired
    ItemRepo ir; // prr used to perform operations on 'pickup_request' table

    BCryptPasswordEncoder encoder; // will be used to encode the password 

    // 0 arg constructor
    public UserServices()
     {
       encoder = new BCryptPasswordEncoder(); // default strength is 10 hence , I am not passing any argument , if you want any other strenght then you can pass it as an argument
     }

    private String epValidation(String email,String password) // Email and password validation
     {
       // boolean result = true; // let's assume email and password is valid 
       String msg = "valid email and password"; // Created an empty string

       // Email regex
       String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
   
       // Password regex:
       // 8–12 chars, 1 digit, 1 uppercase, 1 special
       String passPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$";

       // Validation
        if(email.length()==0 && password.length()==0) // Empty email and password is sent
          msg = "Email and password can't be empty";
        else if (email == null || !email.matches(emailPattern)) // empty email or email is not empty but invalid 
        {
           msg = "Invalid email format";
           //result = false;
        }
       else if (password == null || !password.matches(passPattern)) // empty password or password is not empty but invalid
        {
            msg = "Password must be 8-12 chars, 1 uppercase, 1 digit, 1 special symbol";
            //result = false;
        }
       System.out.println("epValidation() returns "+msg);
       return (msg);
     }
    public ApiResponse login(String email,String password)
     {
       ApiResponse response = new ApiResponse(); // response object which contains message and token(if login credentials are correct) 
       String msg = epValidation(email, password);
       // boolean result = false; // let's assume email address don't match or password is wrong 
       if(msg.equals("valid email and password")) // Valid email and password
       {
         System.out.println("Valid email and password");
         User temp = ur.findByEmail(email); // will point to the database user matching with received email id from the frontend
  
         if(temp!=null && encoder.matches(password,temp.getPassword())) // Registered email and password match
          {
            response.setToken(JwtUtil.generateToken(email)); // get the new token and add it into response object of type 'ApiResponse'
            msg = "Login successful";
            //result = true;
          }
         else if(temp!=null && !encoder.matches(password,temp.getPassword())) // Registered email but wrong password
          {
            msg = "wrong password";
            //result = false;
          }
         else 
         {
           msg = "Email Address is not registered";
           //result = false;
         }
       }
      System.out.println(msg);
      response.setMessage(msg);
      return response;
      // return msg;
     }
    public ApiResponse createAccount(User user)
     {
       ApiResponse response = new ApiResponse(); // response object which contains message and token(if login credentials are correct) 
       System.out.println(user.toString());
       // boolean result = false;
       String msg = epValidation(user.getEmail(), user.getPassword());
       if(msg.equals("valid email and password")) // Valid email and password
       {
          if (ur.findByEmail(user.getEmail())!=null) // If username already used
           {
              msg = "Username already exists";
              //result = false;
           } 
          else // If username doesn't exists already - then save this user into database
           { 
             System.out.println(user); // toString() will get called automatcially here
             user.setPassword(encoder.encode(user.getPassword())); // encode the password and updtaed it in user object
             ur.save(user);
             msg = "Account created successfully";
             //result = true;
           }
        }
       response.setMessage(msg);
       return response;
       // return (msg); - use if returning string

     } // method ends here
    public void saveRequest(RequestDTO reqDTO, String pureJWT)
     {
       String username = JwtUtil.extractUsername(pureJWT); // Take username from pure JWT - in our case username is email 
       User ref_User = ur.findByEmail(username); // returns teh User object from database
       PickupRequest ref_PickupRequest = saveToPickupRequest(ref_User,reqDTO.getRecyclerEmail()); // returns the PickupRequest object from database
       saveToItem(ref_PickupRequest, ref_User, reqDTO.getItems());

     }
    // to save information of PickupRequest in the table 'pickup_request'
    public PickupRequest saveToPickupRequest(User ref_User, String recyclerEmail) // Stores the data of pickup_request into its respective table
     {
       PickupRequest pr = new PickupRequest(ref_User, rr.findByEmail(recyclerEmail), LocalDateTime.now(),"Sent"); // created an object of class 'PickupRequest'
       return (prr.save(pr)); // save the PickupRequest data in 'pickup_request' table and returns it's object reference
     }
    // to save information of items in the table 'item'
    public void saveToItem(PickupRequest ref_PickupRequest, User ref_User, List<ItemDTO> items) // Stores the data of pickup_request into its respective table
     {
       for (ItemDTO i : items){
          Item obj = new Item();
          obj.setRequest(ref_PickupRequest);
          obj.setUser(ref_User);
          obj.setItemType(i.getItemType());
          obj.setQuantity(i.getQuantity());
          ir.save(obj);
        }
     }
} // class ends here
