package com.kkt.learning.config;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class MongoDBConfiguration  {
    @Bean
    public MongoClient mongoClient(@Value("${db.mongodb.uri}") String mongoUri) {
        return MongoClients.create(mongoUri);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory factory) {
        return new ReactiveMongoTemplate(factory);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(MongoClient client) {
        return new SimpleReactiveMongoDatabaseFactory(client, "mongodb");
    }

    @Bean
    public Mono<Void> createCollection(MongoClient client) {
        return Mono.fromRunnable(() -> {
            MongoDatabase database = client.getDatabase("mongodb");
            database.createCollection("myCollection");
        });
    }
}
