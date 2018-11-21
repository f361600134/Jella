package fatiny.myTool.Jredis.older.tool;

import java.util.Arrays;
import java.util.Properties;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import fatiny.myTool.Jredis.older.util.PropertiesUtil;

public class PoolTool {


	/**
	 * @param propertiesFile
	 * @return
	 */
	public static ShardedJedisPool getPool(String propertiesFile) {
		Properties properties = PropertiesUtil.getProperties(propertiesFile);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(properties
				.getProperty("redis.maxTotal")));
		poolConfig.setMaxIdle(Integer.parseInt(properties
				.getProperty("redis.maxIdle")));
		poolConfig.setTestWhileIdle(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setMaxWaitMillis(10000);
		poolConfig.setMinEvictableIdleTimeMillis(1800000);
		JedisShardInfo redisShard = new JedisShardInfo(
				properties.getProperty("redis.ip"), Integer.parseInt(properties
						.getProperty("redis.port")));
		redisShard.setPassword(properties.getProperty("redis.auth"));
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig,
				Arrays.asList(redisShard));
		return pool;
	}
}
