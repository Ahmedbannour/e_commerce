package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
