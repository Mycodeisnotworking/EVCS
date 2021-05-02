package entities;

import java.sql.Timestamp;

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
public class Review_Charger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long review_id;
	@Column(length=255)
	private String title;
	@Column(columnDefinition="TEXT")
	private String body;
	@Column(length=32)
	private String writer;
	@Column(nullable = false)
	private Timestamp writedate;
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	public Review_Charger() {}
	public Review_Charger(String title, String body, String writer, Timestamp writedate) {
		this.title=title;
		this.body=body;
		this.writer=writer;
		this.writedate=writedate;
		}
}
