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
public class Alram {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alram_id")
	private Long id;

	@Column(length=255)
	private String userId;
	
	//station은 charger 통해서 찾으세요. 자주 필요하게되면 추가함.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Builder
	public Alram(Long id, String userId, Charger charger, User user) {
		this.id=id;
		this.userId=userId;
		this.charger=charger;
		this.user = user;
		}
}
