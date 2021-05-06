package com.kw.evcs.service;

import com.kw.evcs.domain.entity.ChargingStation;
import com.kw.evcs.domain.repository.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    @Transactional
    public void save(ChargingStation chargingStation) {
        chargingStationRepository.save(chargingStation);
    }

    public Map<String, ChargingStation> findAllMap() {
        return chargingStationRepository.findAll().stream()
                .collect(Collectors.toMap(
                        ChargingStation::getCode,
                        chargingStation -> chargingStation
                ));
    }
}
