package com.rajat.springboot.practice.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@CreatedDate
	@CreationTimestamp
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private Instant createdAt;

	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false, length = 20, updatable = false)
	private String createdBy;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "UPDATED_AT", insertable = false)
	private Instant updatedAt;

	@LastModifiedBy
	@Column(name = "UPDATED_BY", length = 20, insertable = false)
	private String updatedBy;

	public BaseEntity() {
	}

	public BaseEntity(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
