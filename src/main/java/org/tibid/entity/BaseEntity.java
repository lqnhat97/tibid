package org.tibid.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private Long id;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private Long createdDate;

	@CreatedBy
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@LastModifiedDate
	@Column(name = "updated_date", nullable = false)
	private Long updatedDate;

	@LastModifiedBy
	@Column(name = "updated_by", nullable = false)
	private String updateBy;
}