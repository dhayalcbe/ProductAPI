package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	
	@Value("${service.review.api.save}")
	private String api;
	@Bean
    public ReviewRestClient reviewRestClient() {
        return new ReviewRestClient(new RestTemplate(), api);
    }

}
