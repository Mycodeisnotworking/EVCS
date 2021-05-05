package com.kw.evcs.domain.repository;

import com.kw.evcs.domain.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {
}
