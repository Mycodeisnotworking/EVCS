package com.kw.evcs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ReviewCharger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_charger_id")
	private Long id;
	@Column(length=255)
	private String title;
	@Column(columnDefinition="TEXT")
	private String body;
	@Column
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="charging_station_id")
	private ChargingStation chargingStation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public ReviewCharger(Long id, String title, String body, LocalDateTime createdAt, LocalDateTime modifiedAt, ChargingStation chargingStation, User user) {
		this.id=id;
		this.title=title;
		this.body=body;
		this.createdAt=createdAt;
		this.modifiedAt=modifiedAt;
		this.chargingStation=chargingStation;
		this.user=user;
		}
}
