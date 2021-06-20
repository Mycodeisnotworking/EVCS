package com.kw.evcs.service;

import com.kw.evcs.domain.entity.Agency;
import com.kw.evcs.domain.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;

    public Agency findById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Map<String, Agency> findAllMap() {
        return agencyRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Agency::getCode,
                        agency -> agency
                ));
    }

    @Transactional
    public void save(Agency agency) {
        agencyRepository.save(agency);
    }

    @Transactional
    public void saveAll(Collection<Agency> agencyList) {
        agencyRepository.saveAll(agencyList);
    }
}
