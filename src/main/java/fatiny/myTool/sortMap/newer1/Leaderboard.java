package fatiny.myTool.sortMap.newer1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import fatiny.myTool.sortMap.common.SortedValueMap;

/**
 * @author Jeremy Feng
 * 对之前的RankSoter不能复用,感觉到悲哀
 * 新增Leaderboard类, 再一次的对SortedValueMap进行封装
 * 主要改动: 更加细粒度的提取公共元素,一次写成工具, 到处使用
 * @warning 线程不安全
 * @memory 虽然这个版本的工具不错, 但是还有更好的优化空间
 */
public class Leaderboard<K, V>{
	
	/*** 默认初始容量,-1表示无限制*/
    private static final int DEFAULT_CAPACITY = -1;
	
	/**
     * 最大容量,-1表示不限制
     * 超过最大量的数据会被移除掉
     */
	private int maximum;
	
	/**
	 * 存储当前集合最小的值,用于直接对比
	 */
	//private int minValue;

	/*** 缓存后的排序数据 */
	private SortedValueMap<K, ISorter<K>> map;
	
	/*** 比较器*/
	private Comparator<ISorter<K>> comparator;
	
	public Leaderboard(Comparator<ISorter<K>> comparator){
		this.maximum = DEFAULT_CAPACITY;
		this.map = new SortedValueMap<K, ISorter<K>>(comparator);
		this.comparator = comparator;
	}
	
	public Leaderboard(Comparator<ISorter<K>> comparator, Map<K, ISorter<K>> map){
		this.maximum = DEFAULT_CAPACITY;
		this.map = new SortedValueMap<K, ISorter<K>>(comparator, map);
		this.comparator = comparator;
	}
	
	public Leaderboard(Comparator<ISorter<K>> comparator, int maximum){
		this.map = new SortedValueMap<K, ISorter<K>>(comparator);
		this.comparator = comparator;
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
	 * 适用于一条一条插入数据
	 * 溢出则移除,小量数据<1000看不出效率. 数据量>1000则不适用
	 */
	private void removeIfoverflow(){
		if (maximum > DEFAULT_CAPACITY && map.size() > maximum) {
			int count = map.size() - maximum;
			for (int i = 0; i < count; i++) {
				ISorter<K> v = map.getLast();
				map.remove(v.getId());
			}
		}
	}
	
	/**
	 * 溢出则移除, 适用于多条溢出数据进行移除
	 * @change 重写方法. 效率比之前removeIfoverflow好太多!
	 * 获取到指定排名对象, 截取对象之前的所有数据.复制出来.
	 * @note 为了节省内存空间
	 * 1.复制数据的时候, 对数据量小的那部分进行复制.
	 * 2.移除数据的时候, 对原数据进行保留
	 * @warring 要对此进行测试
	 */
	private void removeOverflow(){
		int size = map.size();
		if (maximum > DEFAULT_CAPACITY && size > maximum) {
			ISorter<K> r = getRankInfo(maximum);
			int diff = size - maximum;
			if (diff > maximum) {
				Collection<ISorter<K>> irs = tailSet(r);
				this.map = new SortedValueMap<K, ISorter<K>>(comparator);
				for (ISorter<K> iRanker : irs) 
					putIfAbove(iRanker.getId(), iRanker);
			}else{
				//复制一需要删除的那部分代码
				Collection<ISorter<K>> rs = new ArrayList<ISorter<K>>(subSet(r, map.getLast()));
				//移除
				for (ISorter<K> iRanker : rs) 
					remove(iRanker.getId());
			}
		}
	}
	
	public Collection<ISorter<K>> headSet(ISorter<K> r){
		return map.headSet(r);
	}
	
	/**
	 * 截取大于等于指定指定参数排名的集合
	 * @param operationRank
	 * @return
	 */
	public Collection<ISorter<K>> tailSet(ISorter<K> r){
		return map.tailSet(r);
	}
	
	/**
	 * 截取大于等于指定指定参数排名的集合
	 * @param operationRank
	 * @return
	 */
	public Collection<ISorter<K>> subSet(ISorter<K> fromValue, ISorter<K> toValue){
		return map.subSet(fromValue, toValue);
	}
	
	/**
	 * 返回截取的指定开始到指定结束的数据,足则返回,不足则返回全部.
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public Collection<ISorter<K>> subRankInfo(int fromIndex, int toIndex){
		return map.subRank(fromIndex, toIndex);
	}
	
	/**
	 * 通过排名,获取到对象
	 * @param index
	 * @return RankInfo
	 */
	public ISorter<K> getRankInfo(int index){
		return map.getRank(index);
	}
	
	
	//================猥琐的分割线===============
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
	
	public ISorter<K> get(Object key) {
		return map.get(key);
	}

	/**
	 * 直接插入数据,然后进行排序.最后移除掉溢出部分
	 * 效率相比较putIfAbove,较低
	 * @param key
	 * @param value
	 * @return
	 */
	public void put(K key, ISorter<K> r) {
		map.put(key, r);
		removeIfoverflow();
	}
	
	/**
	 * 对put 插入进行优化,插入之前利用比较器进行比较,如果符合条件进行插入,否则直接跳过
	 * @change
	 * 如果没有限制容量,效率没有差别
	 * 如果有限制容量, 数据上万之后,性能提升明显,10000条数据效率是put的十倍左右.
	 * 1.有指定排行长度.
	 * 2.新值大于榜内最小值.
	 * @param operationRank
	 * @return true:插入成功. false: 插入失败overflow
	 */
	public boolean putIfAbove(K key, ISorter<K> r){
		//如果排行榜满了,插入新数据需要判断是否大于榜内最小值
		if (maximum > DEFAULT_CAPACITY && maximum == map.size()) {
			int result = comparator.compare(map.getLast(), r);
			if (result > 0) {
				put(key, r);
				return true;
			}
		}else{
			put(key, r);
			return true;
		}
		return false;
	}

	public ISorter<K> remove(Object key) {
		return map.remove(key);
	}

	/**
	 * 先插入所有数据, 进行排序.然后再移除溢出部分
	 */
	public void putAll(Map<? extends K, ? extends ISorter<K>> m) {
		map.putAll(m);
		removeOverflow();
	}
	
	/**
	 * 先插入所有数据, 进行排序.然后再移除溢出部分.
	 * 效率
	 */
	public void putAllItems(Map<? extends K, ? extends ISorter<K>> m) {
		//优化了旧方法
		for(Map.Entry<? extends K, ? extends ISorter<K>> entry : m.entrySet()){
			putIfAbove(entry.getKey(), entry.getValue());
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
	public Collection<ISorter<K>> values() {
		return map.values();
	}

	public Set<java.util.Map.Entry<K, ISorter<K>>> entrySet() {
		return null;
	}
	
	@Override
	public String toString() {
		return "RankSorter [map=" + map + "]";
	}

}
