package com.kw.evcs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Slf4j
@PropertySource("classpath:api-info.properties")
public class ApiClient {

    @Value("${api-path}")
    private String apiPath;
    @Value("${api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(30))
                .build();
    }

    // 1분마다 호출
    @Scheduled(cron = "0 0/1 * * * *")
    public void updateData() throws URISyntaxException {
        log.info("[ApiClient] API Call Scheduler start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String data = restTemplate.getForObject(getUri(), String.class);

        System.out.println(data);
        stopWatch.stop();
        log.info("[ApiClient] API Call Scheduler end | Total elapsed time:{}s", stopWatch.getTotalTimeSeconds());
    }

    private URI getUri() throws URISyntaxException {
        String accessUrl = String.format("%s?ServiceKey=%s", apiPath, apiKey);
        return new URI(accessUrl);
    }
}
