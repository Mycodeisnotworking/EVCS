package entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class ChargingStation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int station_id;
	@Column(nullable = false)
	private Timestamp lastupdate;
	@Column
	private int period;
	@Column(length=255)
	private String statNm;
	@Column(length=255)
	private String statId;
	@Column(length=255)
	private String addr;
	@Column(length=255)
	private String lat;
	@Column(length=255)
	private String lng;
	@Column(length=255)
	private String useTime;
	@Column(length=255)
	private String statUpdDt;
	@Column(length=255)
	private String zcode;
	@Column(length=255)
	private String parkingFree;
	@Column(columnDefinition="TEXT")
	private String info_note;
	
	@OneToMany(mappedBy="chargingstation")
	private List<Charger> chargers = new ArrayList<Charger>();
	
	@OneToMany(mappedBy="chargingstation")
	private List<Agency> agencies= new ArrayList<Agency>();
	
	@Builder
	public ChargingStation() {}	
	@Builder
	public ChargingStation(int station_id, Timestamp lastupdate, int period, String statNm, String statId, String addr, String lat, String lng, String useTime, String statUpdDt,
			String zcode, String parkingFree, String info_note) {
		this.station_id=station_id;
		this.lastupdate=lastupdate;
		this.period=period;
		this.statNm=statNm;
		this.statId=statId;
		this.addr=addr;
		this.lat=lat;
		this.lng=lng;
		this.useTime=useTime;
		this.statUpdDt=statUpdDt;
		this.zcode=zcode;
		this.parkingFree=parkingFree;
		this.info_note=info_note;		
	}

}
