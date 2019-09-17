package com.hf.game.taskino;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisStore {

    private List<JedisCluster> pools;

    public RedisStore() {
        this(URI.create("redis://192.168.154.130:6379/0"),
             URI.create("redis://192.168.154.135:6379/0"),
             URI.create("redis://192.168.154.136:6379/0"),
             URI.create("redis://192.168.154.132:6379/0"),
             URI.create("redis://192.168.154.133:6379/0"),
             URI.create("redis://192.168.154.134:6379/0"));
    }

    public RedisStore(URI... uris) {
        pools = new ArrayList<>();
        Set<HostAndPort>nodes=new HashSet<>(uris.length);
        nodes.add(new HostAndPort("192.168.154.130",6379));
        nodes.add(new HostAndPort("192.168.154.135",6379));
        nodes.add(new HostAndPort("192.168.154.136",6379));
        nodes.add(new HostAndPort("192.168.154.132",6379));
        nodes.add(new HostAndPort("192.168.154.133",6379));
        nodes.add(new HostAndPort("192.168.154.134",6379));
        JedisCluster pool = new JedisCluster(nodes,5000);
        pools.add(pool);
    }

    public void close() {
        for (JedisCluster pool : pools) {
            pool.close();
        }
    }

    public void execute(Consumer<JedisCluster> func) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int index = random.nextInt(pools.size());
        JedisCluster pool = pools.get(index);
        try (Jedis jedis = pool.getConnectionFromSlot(1)) {
            try {
                func.accept(pool);
            } catch (JedisConnectionException e) {
                func.accept(pool);
            }
        }
    }

}
