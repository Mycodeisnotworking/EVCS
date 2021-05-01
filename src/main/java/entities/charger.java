package entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class charger {

	@Id
	private int charger_id;
	
	@Column(nullable = false)
	private Timestamp lastupdate;
	
	@Column
	private int period;
	@Column
	private int station_id;
	@Column(length=255)
	private String chgerId;
	@Column
	private int chgerType;
	@Column
	private int stat;
	@Column(length=255)
	private String powerType;
	@Column
	private int agency_id;
	
}
