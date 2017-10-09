package com.foodtruck.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateClientConfig {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateClientConfig.class);

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) throws Exception {
        log.debug("calling rest operations");
        RestTemplate restTemplate =  new RestTemplate(clientHttpRequestFactory);
        return restTemplate;
    }

}
