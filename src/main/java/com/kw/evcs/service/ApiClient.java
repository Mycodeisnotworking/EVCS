package com.kw.evcs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kw.evcs.domain.entity.Agency;
import com.kw.evcs.domain.entity.Charger;
import com.kw.evcs.domain.entity.ChargingStation;
import com.kw.evcs.web.dto.ChargerInfo;
import com.kw.evcs.web.dto.ChargerStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
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

    @PostConstruct
    public void init() {
        getInitialData();
    }

    /**
     * 서버 시작시 데이터 초기 세팅
     */
    public void getInitialData() {
        StopWatch stopWatch = new StopWatch();
        log.info("[ApiClient] Initial Data Process start");
        stopWatch.start();

        // 조회 쿼리를 줄이기 위해 Map 형태로 미리 조회
        Map<String, Charger> chargerCache = chargerService.findAllMap();
        Map<String, Agency> agencyCache = agencyService.findAllMap();
        Map<String, ChargingStation> chargingStationCache = chargingStationService.findAllMap();

        // saveAll로 한번에 업데이트하기 위해 List형태로 저장
        List<Charger> chargerList = new ArrayList<>();
        for (int i = 1; ; i++) {
            try {
                String data = restTemplate.getForObject(getUri("/getChargerInfo", i, 9999), String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                ChargerInfo.Response response = objectMapper.readValue(data, ChargerInfo.Response.class);

                String resultCode = response.getResultCode();
                if (Objects.isNull(resultCode) || !resultCode.equals("00")) {
                    log.error("[ApiClient] API Result Code is not Success : result code is {}", resultCode);
                    continue;
                }

                if (response.getNumOfRows() <= 0) {
                    break;
                }

                List<ChargerInfo.Item> itemList = response.getItemList();
                for (ChargerInfo.Item item : itemList) {
                    Charger charger = Charger.parse(item);
                    Agency agency = Agency.parse(item);
                    ChargingStation chargingStation = ChargingStation.parse(item);

                    if (!agencyCache.containsKey(item.getBusiId())) { // 나온적 없는 운영기관
                        agencyCache.put(agency.getCode(), agency);
                        charger.setAgency(agency);
                    } else {
                        charger.setAgency(agencyCache.get(agency.getCode()));
                    }

                    if (!chargingStationCache.containsKey(item.getStatId())) { //나온적 없는 충전소
                        chargingStationCache.put(chargingStation.getCode(), chargingStation);
                        charger.setChargingStation(chargingStation);
                    } else {
                        charger.setChargingStation(chargingStationCache.get(chargingStation.getCode()));
                    }

                    if (chargerCache.containsKey(charger.getCode())) {
                        charger.setId(chargerCache.get(charger.getCode()).getId());
                    }
                    chargerList.add(charger);
                }

            } catch (URISyntaxException e) {
                log.error("[ApiClient] Fail to Initial Data Process : URI is not valid");
                continue;
            } catch (JsonProcessingException e) {
                log.error("[ApiClient] Fail to Initial Data Process : Object mapping fail");
                continue;
            }
        }

        agencyService.saveAll(agencyCache.values());
        chargingStationService.saveAll(chargingStationCache.values());
        chargerService.saveAll(chargerList);
        stopWatch.stop();
        log.info("[ApiClient] Initial Data Process complete | Total elapsed time:{}s", stopWatch.getTotalTimeSeconds());
    }

    /**
     * 충전기 상태를 1분마다 업데이트하는 스케줄러
     */
    @Scheduled(cron = "0 * * * * *")
    public void updateStatus() {
        StopWatch stopWatch = new StopWatch();
        log.info("[ApiClient] Charger Status Update start");
        stopWatch.start();
        List<Charger> chargerList = new ArrayList<>(); // 업데이트할 충전기 리스트

        for (int i = 1; ; i++) {
            try {
                String data = restTemplate.getForObject(getUri("/getChargerStatus", i, 9999), String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                ChargerStatus.Response response = objectMapper.readValue(data, ChargerStatus.Response.class);

                String resultCode = response.getResultCode();
                if (Objects.isNull(resultCode) || !resultCode.equals("00")) {
                    log.error("[ApiClient] API Result Code is not Success : result code is {}", resultCode);
                    continue;
                }

                if (response.getNumOfRows() <= 0) {
                    break;
                }

                List<ChargerStatus.Item> itemList = response.getItemList();
                for (ChargerStatus.Item item : itemList) {
                    Charger charger = chargerService.findByCode(item.getStatId() + item.getChgerId());
                    charger.updateStatus(item);
                    chargerList.add(charger);
                }
            } catch (URISyntaxException e) {
                log.error("[ApiClient] Fail to Charger Status Update : URI is not valid");
                continue;
            } catch (JsonProcessingException e) {
                log.error("[ApiClient] Fail to Charger Status Update : Object mapping fail");
                continue;
            }
        }

        chargerService.saveAll(chargerList);
        stopWatch.stop();
        log.info("[ApiClient] Charger Status Update complete | Total elapsed time:{}s", stopWatch.getTotalTimeSeconds());
    }

    private URI getUri(String subPath, int pageNo, int numOfRows) throws URISyntaxException {
        String accessUrl = String.format("%s%s?ServiceKey=%s&pageNo=%d&numOfRows=%d", apiPath, subPath, apiKey, pageNo, numOfRows);
        return new URI(accessUrl);
    }
}
