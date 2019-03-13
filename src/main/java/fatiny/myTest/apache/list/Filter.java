package fatiny.myTest.apache.list;

/**
 * 用于条件判断的过滤器
 * 
 * @auth Jeremy
 * @date 2018年12月22日上午11:31:41
 */
public interface Filter<T> {

	boolean condition(T obj);

}
