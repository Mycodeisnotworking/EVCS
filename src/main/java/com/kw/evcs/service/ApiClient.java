package com.kw.evcs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kw.evcs.domain.entity.Agency;
import com.kw.evcs.domain.entity.Charger;
import com.kw.evcs.domain.entity.ChargerInfo;
import com.kw.evcs.domain.entity.ChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@PropertySource("classpath:api-info.properties")
public class ApiClient {

    @Value("${api-path}")
    private String apiPath;
    @Value("${api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ChargerService chargerService;
    private final AgencyService agencyService;
    private final ChargingStationService chargingStationService;

    public ApiClient(RestTemplateBuilder restTemplateBuilder, ChargerService chargerService, AgencyService agencyService, ChargingStationService chargingStationService) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(30))
                .build();
        this.chargerService = chargerService;
        this.agencyService = agencyService;
        this.chargingStationService = chargingStationService;
    }

    // 1분마다 호출
    @Scheduled(cron = "0 0/1 * * * *")
    public void updateData() {
        StopWatch stopWatch = new StopWatch();
        log.info("[ApiClient] API Call Scheduler start");
        stopWatch.start();

        try {
            String data = restTemplate.getForObject(getUri(1, 100), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            ChargerInfo.Response response = objectMapper.readValue(data, ChargerInfo.Response.class);

            String resultCode = response.getResultCode();
            if (Objects.isNull(resultCode) || !resultCode.equals("00")) {
                log.info("[ApiClient] Fail to API Call : result code is fail");
                return;
            }

            Map<String, Charger> chargerCache = chargerService.findAllMap();
            Map<String, Agency> agencyCache = agencyService.findAllMap();
            Map<String, ChargingStation> chargingStationCache = chargingStationService.findAllMap();

            List<ChargerInfo.Item> itemList = response.getItemList();
            for (ChargerInfo.Item item : itemList) {
                Charger charger = Charger.parse(item);
                Agency agency = Agency.parse(item);
                ChargingStation chargingStation = ChargingStation.parse(item);

                if (!agencyCache.containsKey(item.getBusiId())) { // 나온적 없는 운영기관
                    agencyService.save(agency);
                    agencyCache.put(agency.getCode(), agency);
                    charger.setAgency(agency);
                } else {
                    agency.setId(agencyCache.get(item.getBusiId()).getId());
                    agencyService.save(agency); // 새로운 값으로 업데이트
                    charger.setAgency(agency);
                }

                if (!chargingStationCache.containsKey(item.getStatId())) { //나온적 없는 충전소
                    chargingStationService.save(chargingStation);
                    chargingStationCache.put(chargingStation.getCode(), chargingStation);
                    charger.setChargingStation(chargingStation);
                } else {
                    chargingStation.setId(chargingStationCache.get(item.getStatId()).getId());
                    chargingStationService.save(chargingStation);
                    charger.setChargingStation(chargingStation);
                }

                if (chargerCache.containsKey(charger.getCode())) {
                    charger.setId(chargerCache.get(charger.getCode()).getId());
                }
                chargerService.save(charger);
            }

        } catch (URISyntaxException e) {
            log.info("[ApiClient] Fail to API Call : URI is not valid");
            return;
        } catch (JsonProcessingException e) {
            log.info("[ApiClient] Fail to API Call : Object mapping fail");
            return;
        }

        stopWatch.stop();
        log.info("[ApiClient] API Call Scheduler complete | Total elapsed time:{}s", stopWatch.getTotalTimeSeconds());
    }

    private URI getUri(int pageNo, int numOfRows) throws URISyntaxException {
        String accessUrl = String.format("%s?ServiceKey=%s&pageNo=%d&numOfRows=%d", apiPath, apiKey, pageNo, numOfRows);
        return new URI(accessUrl);
    }
}
