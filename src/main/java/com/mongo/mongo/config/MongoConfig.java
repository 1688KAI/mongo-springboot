package com.mongo.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://111.229.189.98:4000");
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "productdb");
    }
}
