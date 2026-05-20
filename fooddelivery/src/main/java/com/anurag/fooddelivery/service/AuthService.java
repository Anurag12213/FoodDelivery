package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.dto.AuthResponseDTO;
import com.anurag.fooddelivery.dto.UserResponseDTO;
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

        User existingUser=repo.findByEmail(user.getEmail());
        if(existingUser!=null) return "User Already Registered";
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser=repo.save(user);
        UserResponseDTO userDTO=new UserResponseDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setName(savedUser.getName());
        userDTO.setRole(savedUser.getRole());

        String token=jwtUtil.generateToken(savedUser.getEmail());

        AuthResponseDTO authResponse=new AuthResponseDTO();
        authResponse.setToken(token);
        authResponse.setUser(userDTO);
        return authResponse;

    }

    //login
    public Object login(LoginRequest request){
        //userGet either user or null
        User user=repo.findByEmail(request.getEmail());

        if(user == null) return "User Not Found";
        //Compare request password and user password
        boolean isMatch=encoder.matches(request.getPassword(), user.getPassword());

        if(!isMatch)  return "Wrong Password";
        //generating token using jwtUtill   method
        String token= jwtUtil.generateToken(user.getEmail());

        //userDto creta and fields save using user
        UserResponseDTO userDTO =
                new UserResponseDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        //authDto create and save fields with token and userDto
        AuthResponseDTO authResponse =
                new AuthResponseDTO();

        authResponse.setToken(token);
        authResponse.setUser(userDTO);

        return authResponse;
    }
}
