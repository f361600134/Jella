package fatiny.myTool.Jredis.older.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.alibaba.fastjson.JSON;

/**
 * redis客户端包装器
 * 
 * @author sq
 */
public abstract class RedisClient {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected ShardedJedisPool pool;

	/**
	 * ok
	 */
	public static final String OK = "OK";

	/**
	 * 不存在
	 */
	public static final String NX = "NX";

	/**
	 * 存在
	 */
	public static final String XX = "XX";

	/**
	 * 以秒为过期单位
	 */
	public static final String EX = "EX";

	/**
	 * 以毫秒为过期单位
	 */
	public static final String PX = "PX";

	public RedisClient(String propertiesFile) {
		pool = PoolTool.getPool(propertiesFile);
	}

	/**
	 * @param ip
	 * @param port
	 * @param maxTotal
	 * @param maxIdle
	 * @param password
	 */
	public RedisClient(String ip, int port, int maxTotal, int maxIdle,
			String password) {
		// JedisPoolConfig poolConfig = new JedisPoolConfig();
		// poolConfig.setMaxTotal(maxTotal);
		// poolConfig.setMaxIdle(maxIdle);
		// poolConfig.setTestWhileIdle(true);
		// poolConfig.setTestOnReturn(true);
		// poolConfig.setMaxWaitMillis(10000);
		// poolConfig.setMinEvictableIdleTimeMillis(1800000);
		// JedisShardInfo redisShard = new JedisShardInfo(ip, port);
		// redisShard.setPassword(password);
		// pool = new ShardedJedisPool(poolConfig, Arrays.asList(redisShard));

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);// 最大连接数, 默认8个
		poolConfig.setMaxIdle(maxIdle);// 最大空闲连接数, 默认8个
		poolConfig.setTestWhileIdle(true);// 在空闲时检查有效性, 默认false
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestOnBorrow(true);// 在获取连接的时候检查有效性, 默认false
		poolConfig.setMaxWaitMillis(10000);// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常,
											// 小于零:阻塞不确定的时间, 默认-1
		poolConfig.setMinEvictableIdleTimeMillis(1800000);// 逐出连接的最小空闲时间
															// 默认1800000毫秒(30分钟)
		JedisShardInfo redisShard = new JedisShardInfo(ip, port);
		redisShard.setPassword(password);
		pool = new ShardedJedisPool(poolConfig, Arrays.asList(redisShard));
	}

	/**
	 * 从对象池获得一个redis连接
	 * 
	 * @return
	 */
	public ShardedJedis getConnect() {
		return pool.getResource();
	}

	/**
	 * 向对象池返回一个使用完毕的连接
	 * 
	 * @param conn
	 */
	public void returnConnect(ShardedJedis conn) {
		if (conn != null) {
			pool.returnResourceObject(conn);
		}
	}
	
	/**
	 * 向对象池返回一个使用完毕的连接, 根据连接状态调用不同的关闭方法
	 * @param conn
	 * @param conectionBroken
	 * @author Jeremy Feng
	 */
	public void closeResource(ShardedJedis conn, boolean conectionBroken){
		try {
			if (conn != null) {
				if (conectionBroken)
					pool.returnBrokenResource(conn);
				else
					pool.returnResource(conn);
			}
		} catch (Exception e) {
			//destory(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭一个连接
	 * @param conn
	 */
	public void close(ShardedJedis conn) {
		if (conn != null) {
			conn.close();
		}
	}
	
	/**
	 * redis不能在多线程间共享使用，线程不安全。
	 * redis异常时，要把reids的connection返回到returnBrokenResource中，
	 * 释放破坏掉的连接。在reidspool里拿redis的conection没用完就不要返回池里，用完在return池里。
	 */
	public void destory(ShardedJedis conn){
		if (conn != null) {
			pool.destroy();
		}
	}

	/**
	 * 向对象池返回一个异常的连接
	 * 
	 * @param conn
	 */
	protected void returnExceptionConnection(ShardedJedis conn) {
		if (conn != null) {
			pool.returnResourceObject(conn);
		}
	}

	/**
	 * 简单序列化
	 */
	protected String encode0(Object o) {
		return JSON.toJSONString(o);
	}

	/**
	 * 简单反序列化
	 */
	protected <T> T decode0(String s, Class<T> cls) {
		return JSON.parseObject(s, cls);
	}

	/**
	 * 序列化
	 */
	protected String encode(Object o) {
		if (o instanceof String) {
			return (String) o;
		} else if (o instanceof Integer) {
			return o.toString();
		} else if (o instanceof Long) {
			return o.toString();
		} else {
			return JSON.toJSONString(o);
		}
	}

	/**
	 * 反序列化
	 */
	@SuppressWarnings("unchecked")
	protected <T> T decode(String s, Class<T> cls) {
		if (s == null) {
			return null;
		}

		if (cls == String.class) {
			return (T) s;
		} else if (cls == Integer.class || cls == int.class) {
			return (T) Integer.valueOf(s);
		} else if (cls == Long.class || cls == long.class) {
			return (T) Long.valueOf(s);
		} else {
			return JSON.parseObject(s, cls);
		}
	}

	/**
	 * 序列化数组
	 */
	protected String[] encodeList(List<Object> ls) {
		String[] res = new String[ls.size()];
		for (int i = 0; i < ls.size(); ++i) {
			res[i] = encode0(ls.get(i));
		}
		return res;
	}

	/**
	 * 反序列化数组
	 */
	protected <T> List<T> decodeList(List<String> ls, Class<T> cls) {
		List<T> res = new ArrayList<>(ls.size());
		for (String s : ls) {
			res.add(decode0(s, cls));
		}
		return res;
	}

	/**
	 * 序列化map
	 */
	protected <T> Map<String, String> encodeMap(Map<String, T> map) {
		if (map == null) {
			return null;
		}

		Map<String, String> res = new HashMap<>();
		for (Map.Entry<String, T> entry : map.entrySet()) {
			String k = entry.getKey();
			T v = entry.getValue();

			res.put(k, encode0(v));
		}

		return res;
	}

	/**
	 * 反序列化map
	 */
	protected <T> Map<String, T> decodeMap(Map<String, String> map, Class<T> cls) {
		if (map == null) {
			return null;
		}

		Map<String, T> res = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();

			res.put(k, decode0(v, cls));
		}

		return res;
	}
}
