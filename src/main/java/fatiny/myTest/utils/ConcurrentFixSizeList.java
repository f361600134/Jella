package fatiny.myTest.utils;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentFixSizeList<E> extends CopyOnWriteArrayList<E>{
	
	/**   
	 * serialVersionUID:变量描述.   
	 */ 
	private static final long serialVersionUID = 1L;
	/**
	 * 默认容量
	 */
	private static final int DEFAULT_CAPACITY = -1;
	private int capacity = DEFAULT_CAPACITY;
	
	public ConcurrentFixSizeList(int capacity) {
		super();
		if (capacity <= 0) {
			throw new IllegalArgumentException("the input parameter {capacity} is not allowed be less than zero");
		}
		this.capacity = capacity;
	}
	
	public ConcurrentFixSizeList(Collection<? extends E> c, int capacity) {
		super(c);
		if (capacity <= 0) {
			throw new IllegalArgumentException("the input parameter {capacity} is not allowed be less than zero");
		}
		this.capacity = capacity;
	}

	@Override
	public boolean add(E e) {
		boolean bool = super.add(e);
		removeIfOverflow();
		return bool;
	}
	
	@Override
	public boolean addIfAbsent(E e) {
		boolean bool = super.addIfAbsent(e);
		removeIfOverflow();
		return bool;
	}
	
	@Override
	public void add(int index, E element) {
		super.add(index, element);
		removeIfOverflow();
	}
	
	@Override
	public int addAllAbsent(Collection<? extends E> c) {
		int added = super.addAllAbsent(c);
		removeIfOverflow();
		return added;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean bool = super.addAll(c);
		removeIfOverflow();
		return bool;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean bool = super.addAll(index, c);
		removeIfOverflow();
		return bool;
	}
	
	/**
	 * 如果溢出, 则移除第一个
	 * @return  
	 * @return boolean  
	 * @date 2019年3月16日下午3:29:50
	 */
	private void removeIfOverflow() {
		while (super.size() > capacity) {
			super.remove(0);
		}
	}
	
}
