package entities;

import java.time.LocalDateTime;

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
	@Column(nullable = false)
	private LocalDateTime writeDate;
	@ManyToOne
	@JoinColumn(name="charger_id")
	private Charger charger;
	
	public ReviewCharger() {}
	public ReviewCharger(String title, String body, String writer, LocalDateTime writeDate) {
		this.title=title;
		this.body=body;
		this.writer=writer;
		this.writeDate=writeDate;
		}
}
