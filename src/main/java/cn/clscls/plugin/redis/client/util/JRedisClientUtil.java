package cn.clscls.plugin.redis.client.util;

import com.google.common.collect.Maps;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JRedisClientUtil {

    private static final Map<String, JedisCluster> jedisClients = Maps.newConcurrentMap();

    public static JedisCluster getInstance(String hostPorts) {
        Set<HostAndPort> hostAndPorts =
                Arrays.stream(hostPorts.split(",")).map(HostAndPort::from).collect(Collectors.toSet());

        return jedisClients.computeIfAbsent(hostPorts, (key) -> new JedisCluster(hostAndPorts, 100));
    }
}
