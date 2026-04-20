package com.example.Backend.Filter;
import com.example.Backend.Util.JwtUtil; // To use the class JwtUtil
import org.springframework.stereotype.Component; // to use @Component annotation
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter; // to use abstrct class 'OncePerRequestFilter'

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws IOException, ServletException {
        
        String path = request.getRequestURI();

        // Allow public API's wothout JWT - no need of token verification
        if (path.equals("/api/login") || path.equals("/api/createAccount")) {
            chain.doFilter(request, response);
            return; // stop executing further filter logic
        }          
        System.out.println("Path: " + request.getRequestURI());

        // Enforcing JWT on other protected API's
        String header = request.getHeader("Authorization"); // returns the token 
        System.out.println("Header : "+header);
        // A token starts with word 'Bearer'+space+actual token
        if (header != null && header.startsWith("Bearer ")) 
         {
           String token = header.substring(7); // remove the Bearer+space from token and return the actual token part  or pure JWT 
           try {
               JwtUtil.validateToken(token); // validate the token
               String username = JwtUtil.extractUsername(token);
               UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
               SecurityContextHolder.getContext().setAuthentication(auth);
           } catch (Exception e) {
               e.printStackTrace();
               response.setStatus(401);
               return;
           }
         }
        else // If the header is null or token doesn't starts with word 'Bearer'
         {
           response.setStatus(401);
           return;
         }
         
        chain.doFilter(request, response); // continue - next filter will execute after this
    }
}
