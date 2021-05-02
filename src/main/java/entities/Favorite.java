package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity
public class Favorite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="favoriteId")
	private Long id;
	@Column(length=255)
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	public Favorite() {}
	public Favorite(String userId) {
		this.userId=userId;
		}
}
