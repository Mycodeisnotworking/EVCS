package com.kw.evcs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ReviewCharger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private Long id;
	@Column(length=255)
	private String title;
	@Column(columnDefinition="TEXT")
	private String body;
	@Column(length=32)
	private String writer;
	@Column
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime modifiedAt;
	
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public ReviewCharger(Long id, String title, String body, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt, Charger charger, User user) {
		this.id=id;
		this.title=title;
		this.body=body;
		this.writer=writer;
		this.createdAt=createdAt;
		this.modifiedAt=modifiedAt;
		this.charger=charger;
		this.user=user;
		}
}
