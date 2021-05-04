package com.kw.evcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Favorite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="favorite_id")
	private Long id;
	@Column(length=255)
	private String userNickName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Builder
	public Favorite(Long id, String userNickName, Charger charger, User user) {
		this.id=id;
		this.userNickName=userNickName;
		this.charger=charger;
		this.user=user;
		}
}
