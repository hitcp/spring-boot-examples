package org.microframework.elasticsearch.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * @author Shaoyu Liu
 * @date 2022-08-19
 */
@Configuration
//@EnableElasticsearchRepositories(basePackages = "org.springframework.data.elasticsearch.repository")
public class ElasticsearchConfig {
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        // TODO
        return null;
    }


}
