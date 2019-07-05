package fatiny.myTest.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 固定列表数据, 溢出则移除
 * 2. 线程安全
 * 多线程环境下, 对于"读"的数据,不能保证线程安全. 所以读写锁改成
 * @auth Jeremy
 * @date 2019年5月16日下午7:49:39
 */
public class ConcurrentFixSizeArrayList2<E> implements List<E>{
	
	private List<E> list;
	private int capacity = (int)(1L<<4);			 //16
	private final int MAX_CAPACITY = (int) (1L<<14); //最大容量设置为16384
	private final ReentrantLock lock;		 //锁
	
	public ConcurrentFixSizeArrayList2(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("The capacity value is less than 0");
		}
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("The capacity value is great than "+MAX_CAPACITY);
		}
		this.capacity = capacity; //default 16
		this.list = new ArrayList<E>();
		lock = new ReentrantLock();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(E e) {
		try {
			lock.lock();
			boolean b = list.add(e);
			if (b)
				removeFirst();
			return b;
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * 不做加锁, 所有添加调用
	 * @return void  
	 * @date 2019年5月17日下午1:53:36
	 */
	private void removeFirst() {
		while (list.size() > capacity) {
			list.remove(0);
		}
	}

	/**
	 * 单独调用, 写锁
	 */
	@Override
	public boolean remove(Object o) {
		try {
			lock.lock();
			return list.remove(o);
		}finally {
			lock.unlock();
		}
	}

	/**
	 * 读锁
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		try {
			lock.lock();
			return list.containsAll(c);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			lock.lock();
			boolean b = list.addAll(c);
			removeFirst();
			return b;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		try {
			lock.lock();
			boolean b = list.addAll(index, c);
			removeFirst();
			return b;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			lock.lock();
			return list.removeAll(c);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		try {
			lock.lock();
			boolean b = list.retainAll(c);
			return b;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void clear() {
		try {
			lock.lock();
			list.clear();
		}finally {
			lock.unlock();
		}
	}

	@Override
	public E get(int index) {
		try {
			lock.lock();
			//System.out.println(Thread.currentThread().getName()+"==get==>"+this);
			return list.get(index);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public E set(int index, E element) {
		try {
			lock.lock();
			return list.set(index, element);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void add(int index, E element) {
		try {
			lock.lock();
			list.add(index, element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E remove(int index) {
		try {
			lock.lock();
			return list.remove(index);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int indexOf(Object o) {
		try {
			lock.lock();
			return list.indexOf(o);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		try {
			lock.lock();
			return list.lastIndexOf(o);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		try {
			lock.lock();
			return list.listIterator();
		}finally {
			lock.unlock();
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		try {
			lock.lock();
			return list.listIterator(index);
		}finally {
			lock.unlock();
		}
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		try {
			lock.lock();
			return list.subList(fromIndex, toIndex);
		}finally {
			lock.unlock();
		}
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

}
