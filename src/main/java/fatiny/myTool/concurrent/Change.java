package fatiny.myTool.concurrent;

/**
 * @author Jeremy Feng
 *
 */
public class Change implements Runnable {
	
	private int optType;
	ConcurrentStack<Integer> stack;
	
	public Change(int optType, ConcurrentStack<Integer> stack){
		this.stack = stack;
		this.optType = optType;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			if (optType == 1) {
				stack.push(i);
			}else{
				stack.pop();
			}
			System.out.println(stack.next());
		}
	}
	
}
