package fatiny.myTest.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 1. 固定列表数据, 溢出则移除 2. 线程安全 多线程环境下, 对于"读"的数据,不能保证线程安全. 所以读写锁改成
 * 
 * @auth Jeremy
 * @date 2019年5月16日下午7:49:39
 */
public class ConcurrentFixSizeArrayList3<E> implements List<E> {

	private List<E> list;
	private int capacity = (int) (1L << 4); // 16
	private final int MAX_CAPACITY = (int) (1L << 14); // 最大容量设置为16384

	public ConcurrentFixSizeArrayList3(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("The capacity value is less than 0");
		}
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("The capacity value is great than " + MAX_CAPACITY);
		}
		this.capacity = capacity; // default 16
		this.list = new ArrayList<E>();
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
			synchronized (list) {
				boolean b = list.add(e);
				if (b)
					removeFirst();
				return b;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 不做加锁, 所有添加调用
	 * 
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
			synchronized (list) {
				return list.remove(o);
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 读锁
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		try {
			synchronized (list) {
				return list.containsAll(c);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			synchronized (list) {
				boolean b = list.addAll(c);
				removeFirst();
				return b;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		try {
			synchronized (list) {
				boolean b = list.addAll(index, c);
				removeFirst();
				return b;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			synchronized (list) {
				return list.removeAll(c);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		try {
			synchronized (list) {
				boolean b = list.retainAll(c);
				return b;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void clear() {
		try {
			synchronized (list) {
				list.clear();
			}
		} catch (Exception e) {
		}
	}

	@Override
	public E get(int index) {
		try {
			synchronized (list) {
				return list.get(index);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public E set(int index, E element) {
		try {
			synchronized (list) {
				return list.set(index, element);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void add(int index, E element) {
		try {
			synchronized (list) {
				list.add(index, element);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public E remove(int index) {
		try {
			synchronized (list) {
				return list.remove(index);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public String toString() {
		return list.toString();
	}

}
