package lewisl.test1.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

public class ZsortJedisClient {

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("172.26.32.9", 6379);
        Jedis jedis = pool.getResource();
        jedis.auth("tclcddev");
        jedis.select(1);
        jedis.zadd("lewis_zsort_1", 1, "Lewisl");
        jedis.zadd("lewis_zsort_1", 2, "Joycec");
        jedis.zadd("lewis_zsort_1", 3, "Samw");

        pool.returnResource(jedis);
        // get the jedis instance from pool
        Jedis jedis1 = pool.getResource();
        // ZRANGE key start stop [WITHSCORES]
        Set<String> results = jedis1.zrange("lewis_zsort_1", 0, 1);
        for (String res : results) {
            System.out.println(res);
        }
        // get the new one
        Jedis jedis2 = pool.getResource();
        // auth again is needed as the new one not ever been authorized before.
        jedis2.auth("tclcddev");
        jedis2.select(1);
        jedis2.zincrby("lewis_zsort_1", 3, "Samw");

        System.out.println("");
        // return the score and the element
        Set<Tuple> results2 = jedis2.zrangeWithScores("lewis_zsort_1", 0, -1);
        for (Tuple res : results2) {
            System.out.println(res.getScore() + ":" + res.getElement());
        }
        System.out.println("");
        Set<Tuple> results3 = jedis2.zrangeByScoreWithScores("lewis_zsort_1", 1, 2);
        for (Tuple res : results3) {
            System.out.println(res.getScore() + ":" + res.getElement());
        }

        jedis2.zadd("lewis_zsort_2", 30, "Samw");
        jedis2.zinterstore("lewisl_zsort_3", "lewis_zsort_1", "lewis_zsort_2"); // NOT WORKS
        pool.destroy();
    }

}
