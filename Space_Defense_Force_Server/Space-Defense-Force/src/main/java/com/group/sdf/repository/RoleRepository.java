package com.group.sdf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.sdf.entity.Role;
import com.group.sdf.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);
	
}
