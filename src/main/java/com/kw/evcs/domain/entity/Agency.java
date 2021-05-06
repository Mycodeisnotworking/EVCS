package com.kw.evcs.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@Getter
@NoArgsConstructor
@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agency_id")
    private Long id;

    @Column(length = 2, unique = true, nullable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Builder
    public Agency(Long id, String code, String name, String phone) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
    }

    public static Agency parse(ChargerInfo.Item item) {
        return Agency.builder()
                .code(item.getBusiId())
                .name(item.getBusiNm())
                .phone(item.getBusiCall())
                .build();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
