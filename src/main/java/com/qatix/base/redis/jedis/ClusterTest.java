package com.qatix.base.redis.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class ClusterTest {
    public static void main(String[] args) {

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.1.211", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.1.211", 6380));
        jedisClusterNodes.add(new HostAndPort("192.168.1.212", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.1.212", 6380));
        jedisClusterNodes.add(new HostAndPort("192.168.1.213", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.1.213", 6380));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);

//        jc.set("aa","a111");
//        jc.set("ab","a222");
//        jc.set("ac","a333");

        System.out.println(jc.get("aa"));
        System.out.println(jc.get("ab"));
        System.out.println(jc.get("ac"));

//        List<String> res = jc.mget("aa","ab","ac");
//        for (String s :res){
//            System.out.println(s);
//        }
    }
}
