package com.rendiyu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisUtils {
    private static JedisPool jedisPool ;
    static {
        Properties properties = new Properties();
        try {
            properties.load(JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(properties.getProperty("jedis.maxIdle")));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("jedis.maxTotal")));
        config.setMaxWaitMillis(Long.parseLong(properties.getProperty("jedis.maxwaitMillis")));
        jedisPool=new JedisPool(config,properties.getProperty("jedis.host"), Integer.parseInt(properties.getProperty("jedis.port")));

    }

    /**
     *
     * @return
     */
    public static JedisPool getJedisPool (){
        return jedisPool;
    }

    /**
     *
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
