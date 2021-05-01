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
public class Agency {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int agency_id;
	@Column(nullable = false)
	private Timestamp lastupdate;
	@Column
	private int period;
	@Column
	private int station_id;
	@Column
	private int charger_id;
	@Column(length=255)
	private String busiId;
	@Column(length=255)
	private String busiNm;
	@Column(length=255)
	private String busiCall;
	@ManyToOne
	@JoinColumn(name="chargingStation_station_id")
	private ChargingStation chargingstation;
	
	public Agency() {}
	public Agency(int agency_id, Timestamp lastupdate, int period, int station_id, int charger_id, String busiId, String busiNm, String busiCall) {
		this.agency_id=agency_id;
		this.lastupdate=lastupdate;
		this.period=period;
		this.station_id=station_id;
		this.charger_id=charger_id;
		this.busiId=busiId;
		this.busiNm=busiNm;
		this.busiCall=busiCall;
	}
}
