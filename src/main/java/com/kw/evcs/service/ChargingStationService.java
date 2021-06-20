package com.kw.evcs.service;

import com.kw.evcs.domain.entity.ChargingStation;
import com.kw.evcs.domain.repository.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    public Map<String, ChargingStation> findAllMap() {
        return chargingStationRepository.findAll().stream()
                .collect(Collectors.toMap(
                        ChargingStation::getCode,
                        chargingStation -> chargingStation
                ));
    }

    @Transactional
    public void save(ChargingStation chargingStation) {
        chargingStationRepository.save(chargingStation);
    }

    @Transactional
    public void saveAll(Collection<ChargingStation> chargingStationList) {
        chargingStationRepository.saveAll(chargingStationList);
    }
}
