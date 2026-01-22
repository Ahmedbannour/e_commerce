package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
//    Optional<User> findById(Long id);
//    Optional<User> findByUsername(String username);

}
