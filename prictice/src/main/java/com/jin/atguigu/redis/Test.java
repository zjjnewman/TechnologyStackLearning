package com.jin.atguigu.redis;

import com.sun.jndi.toolkit.url.UrlUtil;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {
    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("/Users/jin/workspace/java/properties/redis.properties");
        properties.load(inputStream);
        String host = properties.getProperty("remotehost0");
        String password = properties.getProperty("redispass");
        Jedis jedis = new Jedis(host, 6379);
        jedis.auth(password);
        System.out.println(jedis.ping());
        jedis.set("a", "a");
        jedis.close();
    }
}
