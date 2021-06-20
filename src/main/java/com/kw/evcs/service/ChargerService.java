package com.kw.evcs.service;

import com.kw.evcs.domain.entity.Charger;
import com.kw.evcs.domain.repository.ChargerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ChargerService {

    private final ChargerRepository chargerRepository;

    public Charger findByCode(String code) {
        return chargerRepository.findByCode(code);
    }

    public Map<String, Charger> findAllMap() {
        return chargerRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Charger::getCode,
                        charger -> charger
                ));
    }

    @Transactional
    public void save(Charger charger) {
        chargerRepository.save(charger);
    }

    @Transactional
    public void saveAll(Collection<Charger> chargerList) {
        chargerRepository.saveAll(chargerList);
    }
}
