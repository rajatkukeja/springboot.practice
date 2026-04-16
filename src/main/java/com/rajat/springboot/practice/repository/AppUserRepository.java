package com.rajat.springboot.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajat.springboot.practice.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByEmailAndMobileNumber(String email, String mobileNumber);

	Optional<AppUser> findAppUserByName(String userName);

}
