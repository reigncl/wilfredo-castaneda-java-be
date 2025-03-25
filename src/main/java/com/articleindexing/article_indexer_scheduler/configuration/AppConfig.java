package com.articleindexing.article_indexer_scheduler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * Protected constructor for inheritance purposes.
     * Currently, this class is marked as final to prevent extension.
     */
    protected AppConfig() {
    }

    /**
     * Creates and provides a singleton instance of RestTemplate.
     * @return a new instance of RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
