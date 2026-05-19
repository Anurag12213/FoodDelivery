package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);// derived query method
}
