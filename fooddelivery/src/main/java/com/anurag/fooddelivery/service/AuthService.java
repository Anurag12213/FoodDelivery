package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.entity.User;
import com.anurag.fooddelivery.repository.UserRepository;
import com.anurag.fooddelivery.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.anurag.fooddelivery.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    //create or register
    public Object register(User user){

        User existingUser =
                repo.findByEmail(user.getEmail());

        if(existingUser != null){
            return "User Already Registered";
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        return repo.save(user);
    }

    //login
    public String login(LoginRequest request){
        User user=repo.findByEmail(request.getEmail());
        if(user==null){
            return "User Not Found";
        }
        boolean isMatch=encoder.matches(request.getPassword(),user.getPassword());
        if(isMatch){
            return jwtUtil.generateToken(user.getEmail());
        }
        return "Wrong password";
    }
}
