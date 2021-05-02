package com.kw.evcs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChargingStation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charging_station_id")
    private Long id;

    private String code;

    private String name;

    private String address;

    private String latitude;
    private String longitude;

    private String useTime;

    private String powerType;

    private String zcode;

    private String parkingFree;

    private String description;

    @Builder
    public ChargingStation(Long id, String code, String name, String address, String latitude, String longitude, String useTime, String powerType, String zcode, String parkingFree, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.useTime = useTime;
        this.powerType = powerType;
        this.zcode = zcode;
        this.parkingFree = parkingFree;
        this.description = description;
    }
}
