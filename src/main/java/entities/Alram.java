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
	private Long alram_id;

	@Column(length=255)
	private String user_id;
	
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	public Alram() {}
	public Alram(String user_id) {
		this.user_id=user_id;
		}
}
