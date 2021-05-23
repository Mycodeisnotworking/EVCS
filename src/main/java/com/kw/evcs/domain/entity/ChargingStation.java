package com.kw.evcs.domain.entity;

import com.kw.evcs.web.dto.ChargerInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charging_station_id")
    private Long id;

    @Column(length = 8, unique = true, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String latitude;
    @Column(length = 20, nullable = false)
    private String longitude;

    @Column(length = 50, nullable = false)
    private String useTime;

    @Column(length = 2, nullable = false)
    private String zcode;

    @Column(length = 1)
    private String parkingFree;

    @Column(length = 200)
    private String description;

    @Column(length = 1, nullable = false)
    private String isLimit; // 이용자 제한 Y/N

    @Column(length = 100)
    private String limitDetail; // 이용제한 사유 ex)거주자 외 출입제한

    @Builder
    public ChargingStation(Long id, String code, String name, String address, String latitude, String longitude, String useTime, String zcode, String parkingFree, String description, String isLimit, String limitDetail) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.useTime = useTime;
        this.zcode = zcode;
        this.parkingFree = parkingFree;
        this.description = description;
        this.isLimit = isLimit;
        this.limitDetail = limitDetail;
    }

    public static ChargingStation parse(ChargerInfo.Item item) {
        return ChargingStation.builder()
                .code(item.getStatId())
                .name(item.getStatNm())
                .address(item.getAddr())
                .latitude(item.getLat())
                .longitude(item.getLng())
                .useTime(item.getUseTime())
                .zcode(item.getZcode())
                .parkingFree(item.getParkingFree())
                .description(item.getNote())
                .isLimit(item.getLimitYn())
                .limitDetail(item.getLimitDetail())
                .build();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
