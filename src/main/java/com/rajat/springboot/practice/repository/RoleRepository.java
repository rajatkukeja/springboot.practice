package com.rajat.springboot.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajat.springboot.practice.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	Optional<Roles> findByName(String role);

}
