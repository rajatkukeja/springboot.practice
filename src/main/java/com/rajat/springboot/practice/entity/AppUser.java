package com.rajat.springboot.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
public class AppUser extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "password_hash", nullable = false, length = 500)
	private String passwordHash;

	@Column(name = "mobile_number", length = 20)
	private String mobileNumber;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "role_id")
	private Roles roles;

	public AppUser() {
	}

	public AppUser(long id, String name, String email, String passwordHash, String mobileNumber, Roles roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.mobileNumber = mobileNumber;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
