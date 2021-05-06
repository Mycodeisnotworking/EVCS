package com.kw.evcs.service;

import com.kw.evcs.domain.entity.Charger;
import com.kw.evcs.domain.repository.ChargerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChargerService {

    private final ChargerRepository chargerRepository;

    @Transactional
    public void save(Charger charger) {
        chargerRepository.save(charger);
    }

    public Map<String, Charger> findAllMap() {
        return chargerRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Charger::getCode,
                        charger -> charger
                ));
    }
}
