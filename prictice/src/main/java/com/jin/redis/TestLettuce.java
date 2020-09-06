package com.jin.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.internal.LettuceFactories;
import redis.clients.jedis.Client;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TestLettuce {

    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder().withHost("remotehost1")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> syncRedisCommands = connect.sync();
        RedisAsyncCommands<String, String> asyncRedisCommands = connect.async();
//        String set = syncRedisCommands.set("name", "codehole");
//        System.out.println(syncRedisCommands.get("name"));
        asyncRedisCommands.flushall();
    }

}
