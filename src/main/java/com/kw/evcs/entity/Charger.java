package com.kw.evcs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Charger {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charger_id")
    private Long id;

    private String code;

    private String type;

    private String chargerCondition;

    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charging_station_id")
    private ChargingStation chargingStation;

    @Builder
    public Charger(Long id, String code, String type, String chargerCondition, LocalDateTime updatedDate, Agency agency, ChargingStation chargingStation) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.chargerCondition = chargerCondition;
        this.updatedDate = updatedDate;
        this.agency = agency;
        this.chargingStation = chargingStation;
    }
}
