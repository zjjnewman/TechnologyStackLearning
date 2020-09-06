package com.jin.redis;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

public class BloomFilterTest {

    @Test
    public void guavaBloomFilter(){
        BloomFilter<Integer> integerBloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000000, 0.0001);
        for (int i = 0; i < 1000000; i++) {
            integerBloomFilter.put(i);
        }

        int cnt = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if(integerBloomFilter.mightContain(i)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    @Test
    public void redisBooleanFilter(){
        Jedis jedis = new Jedis();
        Client client = new Client();

    }
}
