package com.kw.evcs.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Agency {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agency_id")
    private Long id;

    private String code;

    private String name;

    private String phone;

    @Builder
    public Agency(Long id, String code, String name, String phone) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
    }
}
