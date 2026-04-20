package com.example.Backend.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // to use @Controller
import org.springframework.web.bind.annotation.*; // tp use @RequestMapping,@PostMapping ,e.t.c
import org.springframework.web.servlet.ModelAndView;
import com.example.Backend.Services.UserServices; // to use the class 'UserServices' - i.e service layer for user relaetd operations
import com.example.Backend.Util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import com.example.Backend.Repositories.UserRepo; // to use 'UserRepo' repository
import com.example.Backend.DTO.ApiResponse; // to use the class 'ApiResponse'
import com.example.Backend.DTO.EmailPassword;
import com.example.Backend.Entities.User; // to use class 'User'
import com.example.Backend.Entities.UserLocation; // to use class 'UserLocation'
import com.example.Backend.DTO.RequestDTO; // to use the class RequestDTO

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo ref; // ref will be used to perform database operations on the table 'user'

    @Autowired
    UserServices us;

    @RequestMapping
    public String accountCreation()
     {
       return "getUser";
     }

    @PostMapping("/login") // @PostMapping is used to specify that the method is used to process the data which is being send
    @ResponseBody
    public ApiResponse login(@RequestBody EmailPassword ep){
       System.out.println("In the login()");
       System.out.println(ep.getEmail()+" "+ep.getPassword());
       return(us.login(ep.getEmail(),ep.getPassword()));
     }
    
    @PostMapping("/createAccount")
    @ResponseBody // Used to return the string not the view 
    public ApiResponse createAccount(@RequestBody User user) // @RequestBody is used when json data is coming from frontend and we want to store it into object directly
     {
        return (us.createAccount(user));
     }
    /* 
    @PostMapping("/saveAddress")
    public void saveAddress(@RequestBody UserLocation ul)
     {
       
     }
     */
    @PostMapping("/saveRequest")
    public void saveRequest(@RequestBody RequestDTO requestDTO,HttpServletRequest request)
     {
       /* 
       No need for validating jwt again because if the control 
       reached here it means it has already passed the validation in JwtFilter.
       String header = request.getHeader("Authorization");
       String pureJWT = null; // by default set to null
       if (header != null && header.startsWith("Bearer ")) {
         pureJWT  = header.substring(7);
       } 
      */
       String header = request.getHeader("Authorization"); // Returns JWT
       String pureJWT  = header.substring(7); // Retunrns pure JWT by removing "Bearer "
       us.saveRequest(requestDTO,pureJWT); // send requestDTO object and pure JWT
     }
    

}
