package com.gomo.movie_streaming_service.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class REST_Configuration {



    @LoadBalanced //  Annotation to mark a RestTemplate, RestClient.Builder or WebClient.Builder bean to be configured to use a LoadBalancerClient.
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
