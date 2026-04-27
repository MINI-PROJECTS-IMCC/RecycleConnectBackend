package com.example.Backend.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // to use @Controller
import org.springframework.web.bind.annotation.*; // tp use @RequestMapping,@PostMapping ,e.t.c
import org.springframework.web.servlet.ModelAndView;
import com.example.Backend.Services.UserServices; // to use the class 'UserServices' - i.e service layer for user relaetd operations
import com.example.Backend.Util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import com.example.Backend.Repositories.UserRepo; // to use 'UserRepo' repository
import com.example.Backend.Entities.*; // to use class 'User', 'Recycler'
import com.example.Backend.Entities.UserLocation; // to use class 'UserLocation'
import com.example.Backend.DTO.*; // to use the DTO classes RequestDTO, RegistrationDTO, e.t.c

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
    public ApiResponse login(@RequestBody LoginDTO loginDTO){
       System.out.println("In the login()");
       System.out.println(loginDTO.getEmail()+" "+loginDTO.getPassword());
       return(us.login(loginDTO.getEmail(),loginDTO.getPassword(),loginDTO.getRole()));
     }
    
    @PostMapping("/createAccount")
    @ResponseBody // Used to return the string not the view 
    public ApiResponse createAccount(@RequestBody RegistrationDTO regDTO) // @RequestBody is used when json data is coming from frontend and we want to store it into object directly
     {
         return (us.createAccount(regDTO));
     }
    /* 
    @PostMapping("/saveAddress")
    public void saveAddress(@RequestBody UserLocation ul)
     {
       
     }
     */
    @PostMapping("/saveRequest")
    public void saveRequest(@RequestBody PickupRequestDTO requestDTO,HttpServletRequest request)
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
    
    @GetMapping("/profileData")
    public AbstractUser profileData(String role,HttpServletRequest request){
       System.out.println("In the controller "+"Role is : "+role);
       String header = request.getHeader("Authorization"); // Returns JWT
       String pureJWT  = header.substring(7); // Retunrns pure JWT by removing "Bearer "
       return(us.profileData(role,pureJWT)); // send requestDTO object and pure JWT and get user data in return
     }
     
    

}
