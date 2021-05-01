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
public class Charger {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@ManyToOne
	@JoinColumn(name="chargingStation_station_id")
	private ChargingStation chargingstation;
	
	public Charger() {}
	public Charger(int charger_id, Timestamp lastupdate, int period, int station_id, String chgerId, int chgerType, int stat, String powerType, int agency_id) {
		this.charger_id=charger_id;
		this.lastupdate=lastupdate;
		this.period=period;
		this.station_id=station_id;
		this.chgerId=chgerId;
		this.chgerType=chgerType;
		this.stat=stat;
		this.powerType=powerType;
		this.agency_id=agency_id;
	}
}
