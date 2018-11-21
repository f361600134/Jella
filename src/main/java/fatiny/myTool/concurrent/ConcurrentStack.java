package fatiny.myTool.concurrent;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import com.google.common.collect.Lists;

public class ConcurrentStack<E> {
	
	AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
	
	public void push(E item){
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do {
			oldHead = top.get();
			newHead.next = oldHead;
		} while (!top.compareAndSet(oldHead, newHead));
	}
	
	public E pop(){
		Node<E> newHead;
		Node<E> oldHead;
		do {
			oldHead = top.get();
			if (oldHead == null) {
				return null;
			}
			newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}
	
	public boolean hasNext(){
		return top.get() != null;
	}
	
	public E next(){
		return top.get().item;
	}
	
	public static class Node<E>{
		
		public final E item;
		public Node<E> next;
		
		public Node(E item){
			this.item = item;
		}
		
	}
	
	public static void main(String[] args) {
		ConcurrentStack<Integer> stack = new ConcurrentStack<Integer>();
		Runnable change1 = new Change(1, stack);
		Runnable change2 = new Change(0, stack);
		List<Runnable> changes = Lists.newArrayList(change1, change2);
		
		Thread thread = null;
		for (Runnable run : changes) {
			thread = new Thread(run);  
			thread.start();
		}
		
//		while (stack.hasNext()) {
//			System.out.println(stack.next());
//		}
	}
}







