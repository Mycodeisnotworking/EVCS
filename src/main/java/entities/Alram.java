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
public class Alram {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alramId")
	private Long id;

	@Column(length=255)
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	public Alram() {}
	public Alram(String userId) {
		this.userId=userId;
		}
}
