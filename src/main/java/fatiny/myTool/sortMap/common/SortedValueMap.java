package fatiny.myTool.sortMap.common;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 线程不安全的排序类
 * @author Jeremy Feng
 * @param <K>
 * @param <V>
 */
public class SortedValueMap<K,V> implements Map<K,V>{
	
	private Map<K, V> map;
    private SortedSet<V> sortedSet;
	
	/**
     * 注意comparator int compared(Object obj1,Object obj2)不能返回 0,
     * 否则sorted认为是同一对象只保存一个. 
     * @param comparator
     */
	public SortedValueMap(Comparator<V> comparator){
    	sortedSet = new TreeSet<V>(comparator);
    	map = new HashMap<K, V>();
    }
	
	/**
	 * 自定义Map类型
	 * @param comparator
	 * @param map
	 */
	public SortedValueMap(Comparator<V> comparator, Map<K, V> map){
    	sortedSet = new TreeSet<V>(comparator);
    	this.map = map;
    }
    
	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		remove(key);
		sortedSet.add(value);
		return map.put(key, value);
	}
	
	@Override
	public V remove(Object key) {
		V v = map.remove(key);
		if(v != null){
			this.removeFromSortedSet(v);
		}
		return v;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()){
			put(entry.getKey(),entry.getValue());
		}
	}

	@Override
	public void clear() {
		map.clear();
		sortedSet.clear();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		return Lists.newArrayList(sortedSet);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return Sets.newHashSet(map.entrySet());
	}

	public V getLast(){
		if (sortedSet.size() > 0) 
			return sortedSet.last();
		else 
			return null;
	}
	
	public V getFirst(){
		if (sortedSet.size() > 0)
			return sortedSet.first();
		else
			return null;
	}
	
	public int getRank(V v){
		SortedSet<V> sub = sortedSet.headSet(v);
		int rank = sub.size() + 1;
		return rank;
	}
	
	private void removeFromSortedSet(V v){
		boolean bool = sortedSet.remove(v);
		if (!bool) {
			/*
			 * 用sortedSet.remove会出现明明对象存在但删不了的情况
			 * 所以改用该方法.
			 * 这种方式, 每次移除对象, 都需要迭代,效率很慢
			 * */
			Iterator<V> it = sortedSet.iterator();
			V temp;
			while(it.hasNext()){
				temp = it.next();
				if(sortedSet.comparator().compare(v,temp) == 0){
					it.remove();
					break;
				}
			}
		}
	}
	
	public void put2SortedSet(V v){
		sortedSet.add(v);
	}
	
	public SortedSet<V> headSet(V v){
		return sortedSet.headSet(v);
	}
	
	public SortedSet<V> tailSet(V v){
		return sortedSet.tailSet(v);
	}
	
	public SortedSet<V> subSet(V fv, V tv){
		return sortedSet.subSet(fv, tv);
	}
	
	/**
	 * 通过排序后的位置找到值
	 */
	public V getRank(int index){
		V v = null;
		Collection<V> vs = subRank(index, index);
		if (vs.iterator().hasNext()) {
			v = vs.iterator().next();
		}
		return v;
	}
	
	/**
	 * 返回截取的指定开始到指定结束的数据,足则返回,不足则返回全部.
	 */
	public Collection<V> subRank(int fromIndex, int toIndex){
		Collection<V> result = Lists.newArrayList();
		V v;
		int count = 1;
		Iterator<V> it = sortedSet.iterator();
		while(it.hasNext()){
			v = it.next();
			if(fromIndex <= count && count <= toIndex){
				result.add(v);
				count ++;
			}else if(count < fromIndex)
				count ++;
			else
				break;
		}
		return result;
	}

	@Override
	public String toString() {
		return "SortedValueMap [map=" + map +"]";
	}
}
