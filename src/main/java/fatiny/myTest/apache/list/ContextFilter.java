package fatiny.myTest.apache.list;

public class ContextFilter implements Filter<Integer> {

	@Override
	public boolean condition(Integer obj) {
		return obj >= 1;
	}

}
