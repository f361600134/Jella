package fatiny.myTool.Jredis.older.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Tuple;

import com.alibaba.fastjson.JSON;

import fatiny.myTool.Jredis.older.util.CommonUtils;
import fatiny.myTool.Jredis.older.util.StringUtils;

/**
 * 基础cache功能
 * @author 
 */
public class RedisCache extends RedisClient {

	public RedisCache(String propertiesFile) {
		super(propertiesFile);
	}

	/**
	 * @param ip
	 * @param port
	 * @param maxTotal
	 *            最大连接数
	 * @param maxIdle
	 *            最大空闲连接数
	 * @param password
	 * @param expireTime
	 */
	public RedisCache(String ip, int port, int maxTotal, int maxIdle,
			String password) {
		super(ip, port, maxTotal, maxIdle, password);
	}

	public boolean check() {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.get("check");
		} catch (Exception e) {
			return false;
		} finally {
			returnConnect(jedis);
		}
		return true;
	}

	/**
	 * 检查key是否存在
	 */
	public boolean exists(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.exists(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * set
	 */
	public String set(String key, Object value) {
		return set(key, 0, value);
	}

	/**
	 * set
	 */
	public String set(String key, int expireTime, Object value) {
		ShardedJedis jedis = null;
		String v = encode(value);
		try {
			jedis = getConnect();
			if (expireTime == 0) {
				return jedis.set(key, v);
			} else {
				return jedis.setex(key, expireTime, v);
			}

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 线程安全
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public int setNx(Object key, Object value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Long result = jedis.setnx(key.toString(), value.toString());
			if (result == null) {
				return 0;
			}
			return result.intValue();
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * set
	 */
	public boolean setNx(String key, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Long result = jedis.setnx(key, "1");
			if (result != null && result == 1) {
				jedis.expire(key, seconds);
				return true;
			}
			return false;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param field
	 * @param value
	 */
	public Long hset(String key, String field, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param field
	 * @param value
	 */
	public Long hincrBy(String key, String field, int value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param score
	 * @param member
	 */
	public long zadd(String key, double score, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zadd(key, score, member);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
		return 0;
	}

	/**
	 * @param key
	 * @param score
	 * @param member
	 */
	public long zincrby(String key, double score, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zincrby(key, score, member).longValue();
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param score
	 * @param member
	 */
	public long zrem(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
		return 0;
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	public <T> T get(String key, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String value = jedis.get(key);
			return decode(value, cls);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回0
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	public int getInt(String key) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String value = jedis.get(key);
			if (StringUtils.isEmpty(value)) {
				return 0;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	public int getInt(ShardedJedis jedis, String key) {

		try {
			String value = jedis.get(key);
			if (StringUtils.isEmpty(value)) {
				return 0;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			logger.error("redis exception", e);
			return 0;
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param cls
	 * @return
	 */
	public <T> List<T> getList(String key, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String value = jedis.get(key);
			if (StringUtils.isEmpty(value)) {
				return null;
			}
			return JSON.parseArray(value, cls);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 设置列表
	 * 
	 * @param key
	 * @param list
	 * @param time
	 */
	public void setList(String key, List<?> list, int time) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			if (time > 0) {
				jedis.setex(key, time, JSON.toJSONString(list));
			} else {
				jedis.set(key, JSON.toJSONString(list));
			}

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	public String get(String key) {
		return get(key, String.class);
	}

	public Long getLong(Object key) {
		return get(key.toString(), Long.class);
	}

	/**
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrange(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zrangeWithScores(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param field
	 * @param cls
	 * @return
	 */
	public <T> T hget(String key, String field, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			String value = jedis.hget(key, field);
			return decode(value, cls);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param field
	 * @param cls
	 * @return
	 */
	public int hgetInt(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			String value = jedis.hget(key, field);
			if (StringUtils.isEmpty(value)) {
				return 0;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param field
	 * @param cls
	 * @return
	 */
	public int hgetInt(ShardedJedis jedis, String key, String field) {
		try {
			String value = jedis.hget(key, field);
			if (StringUtils.isEmpty(value)) {
				return 0;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			logger.error("redis exception", e);
			return 0;
		}
	}

	/**
	 * 返回hash长度
	 * 
	 * @param key
	 * @return
	 */
	public int hlen(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Long r = jedis.hlen(key);
			if (r != null) {
				return r.intValue();
			}
			return 0;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param field
	 * @param cls
	 * @return
	 */
	public <T> List<T> hgetList(String key, String field, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			String value = jedis.hget(key, field);
			if (StringUtils.isEmpty(value)) {
				return null;
			}
			return JSON.parseArray(value, cls);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 没有就返回空
	 * 
	 * @param key
	 * @param field
	 * @param cls
	 * @return
	 */
	public void hsetList(String key, String field, List<?> dataList) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.hset(key, field, JSON.toJSONString(dataList));
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param expireTime
	 * @param value
	 * @return
	 */
	public boolean add(String key, int expireTime, Object value) {
		ShardedJedis jedis = null;
		String v = encode(value);
		try {
			jedis = getConnect();
			if (expireTime == 0) {
				jedis.set(key, v);
			} else {
				jedis.set(key, v, RedisClient.NX, RedisClient.EX, expireTime);
			}
			return true;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * update
	 */
	public boolean update(String key, int expireTime, Object value) {
		ShardedJedis jedis = null;
		String v = encode(value);
		try {
			jedis = getConnect();
			if (expireTime == 0) {
				jedis.set(key, v);
			} else {
				jedis.setex(key, expireTime, v);
			}
			return true;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * delete
	 */
	public Long del(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.del(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * delete
	 */
	public Long hdel(String key, String... fileds) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hdel(key, fileds);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * increase
	 */
	public Long increase(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.incr(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * decrease
	 */
	public Long decrease(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.decr(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	/*------------------------------------------
	 * map cache
	 *------------------------------------------*/

	/**
	 * set one key in a map
	 */
	public void mapSet(String key, String subkey, Object value) {
		ShardedJedis jedis = null;
		String v = encode0(value);
		try {
			jedis = getConnect();
			jedis.hset(key, subkey, v);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception, key=" + key + ",subkey=" + subkey, e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 */
	public <T> void mapSetAll(String key, Map<String, T> map) {
		ShardedJedis jedis = null;

		Map<String, String> map1 = encodeMap(map);
		try {
			jedis = getConnect();
			jedis.del(key);
			jedis.hmset(key, map1);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception key=" + key, e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * get one key in the map
	 */
	public <T> T mapGet(String key, String subkey, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String s = jedis.hget(key, subkey);
			return decode0(s, cls);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 
	 */
	public boolean hexists(String key, String subkey) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			return jedis.hexists(key, subkey);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * get a map totally
	 */
	public <T> Map<String, T> mapGetAll(String key, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			Map<String, String> res = jedis.hgetAll(key);
			return decodeMap(res, cls);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * delete one key in map
	 */
	public boolean mapDelete(String key, String subkey) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			Long res = jedis.hdel(key, subkey);
			return res == 1;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return false;
		} finally {
			returnConnect(jedis);
		}
	}

	/*------------------------------------------
	 * list cache
	 *------------------------------------------*/

	/**
	 * push to the head of list
	 */
	public void listLeftPush(String key, Object obj) {
		ShardedJedis jedis = null;
		String v = encode0(obj);
		try {
			jedis = getConnect();
			jedis.lpush(key, v);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * push to the tail of list
	 */
	public void listRightPush(String key, Object obj) {
		ShardedJedis jedis = null;
		String v = encode0(obj);
		try {
			jedis = getConnect();
			jedis.rpush(key, v);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param obj
	 * @param maxCount
	 *            最大数量限制
	 */
	public void listRightPush(String key, Object obj, int maxCount) {
		ShardedJedis jedis = null;
		String v = encode0(obj);
		try {
			jedis = getConnect();
			if (maxCount > 0) {
				Long count = jedis.llen(key);
				if (count == null) {
					count = 0l;
				}
				while (count > maxCount) {
					count -= 1;
					jedis.lpop(key);
				}
			}
			jedis.rpush(key, v);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param obj
	 * @param maxCount
	 *            最大数量限制
	 */
	public void listLeftPush(String key, Object obj, int maxCount) {
		ShardedJedis jedis = null;
		String v = encode(obj);
		try {
			jedis = getConnect();
			if (maxCount > 0) {
				Long count = jedis.llen(key);
				if (count == null) {
					count = 0l;
				}
				while (count > maxCount) {
					count -= 1;
					jedis.rpop(key);
				}
			}
			jedis.lpush(key, v);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param obj
	 * @param maxCount
	 *            最大数量限制
	 */
	public void listLeftPush(String key, String[] datas, int maxCount) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			if (maxCount > 0) {
				Long count = jedis.llen(key);
				if (count == null) {
					count = 0l;
				}
				while (count > maxCount) {
					count -= 1;
					jedis.rpop(key);
				}
			}
			jedis.lpush(key, datas);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * push to the tail of list
	 */
	public void rpush(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.rpush(key, value);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 
	 * @param key
	 * @param values
	 */
	public void rpush(String key, Collection<String> values) {
		if (CommonUtils.isCollectionEmpty(values)) {
			return;
		}
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			int i = 0;
			String[] data = new String[values.size()];
			for (String value : values) {
				data[i] = value;
				i++;
			}
			jedis.rpush(key, data);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);

		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * pop from the head of list
	 */
	public <T> T listLeftPop(String key, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String s = jedis.lpop(key);
			return decode0(s, cls);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 */
	public String lpop(String key) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			return jedis.lpop(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 */
	public void listLeftDel(String key, int count) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			while (count > 0) {
				jedis.lpop(key);
				count -= 1;
			}
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 获取所有数据并清空
	 */
	public <T> List<T> leftPopAll(String key, Class<T> clz) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			List<String> list = jedis.lrange(key, 0, -1);
			if (CommonUtils.isCollectionEmpty(list)) {
				return null;
			}
			jedis.del(key);
			List<T> result = new ArrayList<T>(list.size());
			for (String str : list) {
				result.add(decode(str, clz));
			}
			return result;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 从头取出指定数量数据
	 */
	public List<String> leftPop(String key, int count) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			List<String> result = new ArrayList<String>(count);
			for (int i = 0; i < count; i++) {
				String s = jedis.lpop(key);
				if (StringUtils.isEmpty(s)) {
					break;
				} else {
					result.add(s);
				}
			}
			return result;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * pop from the tail of list
	 */
	public <T> T listRightPop(String key, Class<T> cls) {
		ShardedJedis jedis = null;

		try {
			jedis = getConnect();
			String s = jedis.rpop(key);
			return decode0(s, cls);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * set a list
	 */
	public void listPushAll(String key, List<Object> ls) {
		ShardedJedis jedis = null;
		String[] ls1 = encodeList(ls);
		try {
			jedis = getConnect();
			jedis.del(key);
			jedis.rpush(key, ls1);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * set a list
	 */
	public void rpush(String key, String[] values) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.del(key);
			jedis.rpush(key, values);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 获取列表长度
	 * 
	 * @param key
	 * @return
	 */
	public int llen(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.llen(key).intValue();

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * get a range from list
	 */
	public <T> List<T> listRange(String key, int begin, int end, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			List<String> ls = jedis.lrange(key, begin, end);
			return decodeList(ls, cls);

		} catch (Exception e) {
			logger.error("redis exception", e);
			returnExceptionConnection(jedis);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 */
	public void lrem(String key, int count, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.lrem(key, count, value);
		} catch (Exception e) {
			logger.error("redis exception", e);
			returnExceptionConnection(jedis);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * get a range from list
	 */
	public <T> List<T> listReverseRange(String key, int begin, int end,
			Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			List<String> ls = jedis.lrange(key, begin, end);
			if (CommonUtils.isCollectionEmpty(ls)) {
				return null;
			}
			List<T> result = new ArrayList<T>(ls.size());
			for (int i = ls.size() - 1; i >= 0; i--) {
				result.add(decode(ls.get(i), cls));
			}
			return result;
		} catch (Exception e) {
			logger.error("redis exception", e);
			returnExceptionConnection(jedis);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @return
	 */
	public <T> T lindex(String key, int index, Class<T> cls) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return decode(jedis.lindex(key, index), cls);

		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @return
	 */
	public void lset(String key, int index, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.lset(key, index, value);

		} catch (Exception e) {
			logger.error("redis exception", e);
			returnExceptionConnection(jedis);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 获取列表并删除
	 * 
	 * @param key
	 * @return
	 */
	public List<String> listRangeAndDelete(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			List<String> ls = jedis.lrange(key, 0, -1);
			jedis.del(key);
			return ls;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevrange(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public void zremrangeByRank(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zscore(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param member
	 * @return
	 */
	public int zscoreInt(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Double d = jedis.zscore(key, member);
			if (d == null) {
				return 0;
			}
			return d.intValue();
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @param member
	 * @return
	 */
	public int zscoreInt(ShardedJedis jedis, String key, String member) {
		try {
			Double d = jedis.zscore(key, member);
			if (d == null) {
				return 0;
			}
			return d.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @param key
	 * @param member
	 * @return
	 */
	public long zscore(ShardedJedis jedis, String key, String member) {
		try {
			Double d = jedis.zscore(key, member);
			if (d == null) {
				return 0;
			}
			return d.longValue();
		} catch (Exception e) {
			logger.error("redis exception", e);
			return 0;
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public Long zcard(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zcard(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public int zcardInt(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Long result = jedis.zcard(key);
			if (result == null) {
				return 0;
			}
			return result.intValue();
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public int zcardInt(ShardedJedis jedis, String key) {
		Long result = jedis.zcard(key);
		if (result == null) {
			return 0;
		}
		return result.intValue();
	}

	/**
	 * @param key
	 * @return
	 */
	public Long incrBy(String key, long value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.incrBy(key, value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public Long decrBy(String key, long value) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.decrBy(key, value);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public Long incr(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.incr(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	/**
	 * 批量增加
	 * 
	 * @param key
	 * @return
	 */
	public void incrBatch(String[] keys, int addCount) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			for (String key : keys) {
				jedis.incrBy(key, addCount);
			}
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
		} finally {
			returnConnect(jedis);
		}
	}

	public Map<String, String> hgetAll(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public String hget(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hget(key, field);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public Set<String> hkeys(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.hkeys(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public Long expireAt(String key, long unixTime) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.expireAt(key, unixTime);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public int zrevrank(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			Long order = jedis.zrevrank(key, member);
			if (order == null) {
				return 0;
			}
			return order.intValue() + 1;
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0;
		} finally {
			returnConnect(jedis);
		}
	}

	public int zrevrank(ShardedJedis jedis, String key, String member) {
		try {
			Long order = jedis.zrevrank(key, member);
			if (order == null) {
				return 0;
			}
			return order.intValue() + 1;
		} catch (Exception e) {
			logger.error("redis exception", e);
			return 0;
		}
	}

	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.zrevrangeWithScores(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public Long expire(String key, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	public List<String> lrange(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}

	public Long lpush(String key, String... strings) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.lpush(key, strings);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}

	public Long ttl(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.ttl(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}
	
	public Long sadd(String key, String... members) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.sadd(key, members);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return 0l;
		} finally {
			returnConnect(jedis);
		}
	}
	
	public Set<String> smembers(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getConnect();
			return jedis.smembers(key);
		} catch (Exception e) {
			returnExceptionConnection(jedis);
			logger.error("redis exception", e);
			return null;
		} finally {
			returnConnect(jedis);
		}
	}
	
	
	
	
}
