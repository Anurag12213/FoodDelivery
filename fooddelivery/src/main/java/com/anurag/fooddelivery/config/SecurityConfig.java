package com.anurag.fooddelivery.config;

import com.anurag.fooddelivery.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        //public api access
                        .requestMatchers("/api/auth/**")
                        .permitAll()
                        //--FOOD Apis

                        //View Foods
                        .requestMatchers(HttpMethod.GET,"/api/foods/**")
                        .hasAnyRole("USER","ADMIN","RESTAURANT_OWNER")

                        //createFood
                        .requestMatchers(HttpMethod.POST,"/api/foods/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")

                        //updateFood
                        .requestMatchers(HttpMethod.PUT,"/api/foods/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")

                        //deleteFood
                        .requestMatchers(HttpMethod.DELETE,"/api/foods/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")

                        //---Restaurant apis

                        //viewRestaurants
                        .requestMatchers(HttpMethod.GET,"/api/restaurants/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER","USER" )

                        //createRestaurants
                        .requestMatchers(HttpMethod.POST,"/api/restaurants/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")

                        //updateRestaurants
                        .requestMatchers(HttpMethod.PUT,"/api/restaurants/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")

                        //deleteRestaurants
                        .requestMatchers(HttpMethod.DELETE,"/api/restaurants/**")
                        .hasAnyRole("ADMIN","RESTAURANT_OWNER")


                        .anyRequest()
                        .permitAll()




                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .formLogin(form -> form.disable())

                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}