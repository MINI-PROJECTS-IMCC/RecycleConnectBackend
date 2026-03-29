package com.example.Backend.Filter;
import com.example.Backend.Util.JwtUtil; // To use the class JwtUtil
import org.springframework.stereotype.Component; // to use @Component annotation
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter; // to use abstrct class 'OncePerRequestFilter'

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws IOException, ServletException {

        String header = request.getHeader("Authorization"); // returns the token 
        // A token starts with word 'Bearer'+space+actual token
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // remove the Bearer+space from token and return the actual token part

            try {
                JwtUtil.validateToken(token); // validate the token
            } catch (Exception e) {
                response.setStatus(401);
                return;
            }
        }
        chain.doFilter(request, response); // continue
    }
}
