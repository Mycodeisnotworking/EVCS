package com.kw.evcs.domain.repository;

import com.kw.evcs.domain.entity.Charger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargerRepository extends JpaRepository<Charger, Long> {

    Charger findByCode(String code);
}
