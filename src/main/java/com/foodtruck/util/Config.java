package com.foodtruck.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rpashara on 10/9/2017.
 */

@Configuration
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) throws Exception {
        log.debug("calling rest operations");
        RestTemplate restTemplate =  new RestTemplate(clientHttpRequestFactory);
        return restTemplate;
    }

    @Bean(name = "clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        log.debug("getting clientHttpRequestFactory");
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean(name = "httpClient")
    public HttpClient httpClient() {
        log.debug("getting httpClient");
        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .build();
        log.debug(httpClient.toString());
        return httpClient;
    }
}
