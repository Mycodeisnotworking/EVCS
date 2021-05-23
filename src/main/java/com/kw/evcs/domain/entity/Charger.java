package com.kw.evcs.domain.entity;

import com.kw.evcs.common.util.DateUtil;
import com.kw.evcs.web.dto.ChargerInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Charger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charger_id")
    private Long id;

    @Column(length = 10, unique = true, nullable = false)
    private String code;

    @Column(length = 2, nullable = false)
    private String type;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ChargerCondition chargerCondition;

    @Column(length = 20)
    private String powerType;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @Column(length = 1, nullable = false)
    private String isRemove; // 없어진 충전기 여부 Y/N

    @Column(length = 100)
    private String removeDetail; // 철거 사유 ex) 고객 요구로 철거

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charging_station_id")
    private ChargingStation chargingStation;

    @Builder
    public Charger(Long id, String code, String type, ChargerCondition chargerCondition, String powerType, LocalDateTime updatedDate, String isRemove, String removeDetail, Agency agency, ChargingStation chargingStation) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.chargerCondition = chargerCondition;
        this.powerType = powerType;
        this.updatedDate = updatedDate;
        this.isRemove = isRemove;
        this.removeDetail = removeDetail;
        this.agency = agency;
        this.chargingStation = chargingStation;
    }

    public static Charger parse(ChargerInfo.Item item) {
        return Charger.builder()
                .code(item.getStatId() + item.getChgerId()) // 충전소 코드와 충전기 코드를 합쳐서 unique하게 만듦
                .type(item.getChgerType())
                .chargerCondition(ChargerCondition.getCondition(item.getStat()))
                .powerType(item.getPowerType())
                .updatedDate(DateUtil.parseDate(item.getStatUpdDt()))
                .isRemove(item.getDelYn())
                .removeDetail(item.getDelDetail())
                .build();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }
}
