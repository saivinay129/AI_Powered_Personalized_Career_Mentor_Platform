package com.backendproject.backend_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;


public class SpringAiConfig {
    @Value("${spring.ai.openai.chat.base-url}")
    private String apiurl;

    @Bean
    public RestClient restClient(){
        return RestClient.builder().baseUrl(apiurl).build();
    }
}
