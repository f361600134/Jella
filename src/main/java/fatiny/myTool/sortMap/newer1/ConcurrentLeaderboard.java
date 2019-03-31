package fatiny.myTool.sortMap.newer1;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import fatiny.myTool.rank.util.SortedValueMap;

/**
 * @author Jeremy Feng
 * @changhe 新增Leaderboard类, 再一次的对SortedValueMap进行封装<br>
 * 主要改动: 更加细粒度的提取公共元素,一次写成工具, 到处使用
 * 这样做需要维护两份代码, 有些没有必要, 可以对
 */
public class ConcurrentLeaderboard<K, V> implements ILeaderboard<K, V>{
	
	/*** 默认初始容量,-1表示无限制*/
    private static final int DEFAULT_CAPACITY = -1;
	
	/**
     * 最大容量,-1表示不限制
     * 超过最大量的数据会被移除掉
     */
	private int maximum;
	
	/*** 缓存后的排序数据,有序集 */
	private SortedValueMap<K, V> map;
	
	//因为排行榜一向读写频繁, 所以决定使用读写锁
	private final ReentrantReadWriteLock lock;
	
	/*** 比较器*/
	private Comparator<V> comparator;
	
	public ConcurrentLeaderboard(Comparator<V> comparator){
		this.maximum = DEFAULT_CAPACITY;
		this.map = new SortedValueMap<K, V>(comparator);
		this.comparator = comparator;
		this.lock = new ReentrantReadWriteLock();
	}
	
	public ConcurrentLeaderboard(Comparator<V> comparator, int maximum){
		this.map = new SortedValueMap<K, V>(comparator);
		this.comparator = comparator;
		this.maximum = maximum;
		this.lock = new ReentrantReadWriteLock();
	}
	
	/**
	 * 设置排行榜最大量
	 * @param maximum
	 */
	public void setMaximum(int maximum) {
		if (maximum == 0) {
			throw new IllegalArgumentException("the input parameter {maximum} is not allowed be zero");
		}
		this.maximum = maximum;
	}
	
	/**
	 * 截取小于等于指定排名的值
	 * @param v
	 * @return
	 */
	public Collection<V> headSet(V r){
		return map.headSet(r);
	}
	
	/**
	 * 截取大于等于指定指定参数排名的集合
	 * @param operationRank
	 * @return
	 */
	public Collection<V> tailSet(V r){
		return map.tailSet(r);
	}
	
	/**
	 * 截取大于等于指定指定参数排名的集合
	 * @param operationRank
	 * @return
	 */
	public Collection<V> subSet(V fromValue, V toValue){
		return map.subSet(fromValue, toValue);
	}
	
	/**
	 * 返回截取的指定开始到指定结束的数据,足则返回,不足则返回全部.
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public Collection<V> subRankInfo(int fromIndex, int toIndex){
		return map.subRank(fromIndex, toIndex);
	}
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty(){
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}
	
	public V get(Object key) {
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		boolean bool = putIfAbove(key, value);
		return bool ? value : null;
	}
	
	/**
	 * 对put 插入进行优化,插入之前利用比较器进行比较,如果符合条件进行插入,否则直接跳过
	 * 优化后的putIfAbove在数据上万之后,性能提升明显,10000条数据效率是put的十倍左右.
	 * 1.有指定排行长度.
	 * 2.新值大于榜内最小值.
	 * @param operationRank
	 * @return true:插入成功. false: 插入失败
	 */
	private boolean putIfAbove(K key, V value){
		lock.writeLock().tryLock();
		try {
			//如果排行榜满了,插入新数据需要判断是否大于榜内最小值
			if (maximum > DEFAULT_CAPACITY && maximum == map.size()) {
				int result = comparator.compare(map.getLast(), value);
				if (result > 0) {
					map.put(key, value);
					removeIfoverflow();
					return true;
				}
			}else{
				map.put(key, value);
				removeIfoverflow();
				return true;
			}
			return false;
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	/**
	 * 溢出则移除,小量数据适用. 效率会随着数据量的增长而下降
	 */
	private void removeIfoverflow(){
		if (maximum > DEFAULT_CAPACITY && map.size() > maximum) {
			int count = map.size() - maximum;
			for (int i = 0; i < count; i++) {
				V v = map.getLast();
				//map.remove(v.getId());
				map.removeFromSortedSet(v);
			}
		}
	}

	public V remove(Object key) {
		lock.writeLock().tryLock();
		try {
			return map.remove(key);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public void clear(){
		map.clear();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	/**
	 * 返回排行榜数据
	 */
	public Collection<V> values() {
		return map.values();
	}

	/**
	 * 先插入所有数据, 进行排序.然后再移除溢出部分.
	 * 效率略高
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		//优化了旧方法
		for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()){
			putIfAbove(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return map.entrySet();
	}
	
	/**
	 * 获取到排行榜头条数据
	 */
	public V getFirst(){
		return map.getFirst();
	}
	
	/**
	 * 获取到最后一条数据
	 */
	public V getLast(){
		return map.getLast();
	}
	
	/**
	 * 通过排名,获取到对象
	 * @param index
	 * @return RankInfo
	 */
	public V getValueByIndex(int index){
		return map.getRank(index);
	}
	
	/**
	 * 返回排名数据
	 * 不管有无在榜内,都会产生排行数据
	 */
	public Integer getRanking(V s){
		int ranking = map.getRank(s);
		//100名以外的排名,返回-1
		if (maximum > DEFAULT_CAPACITY && ranking > maximum) {
			ranking = -1;
		}
		return ranking;
	}
	
 }
