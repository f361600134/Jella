package fatiny.myTool.sortMap.newer1;

/**
 * 加强版, 对接口使用了泛型.
 * @author Jeremy Feng
 * @param <K>
 */
public interface ISorter<K> {
	
	/**
	 * 对象唯一id
	 * @return
	 */
	public K getId();
	
	/**
	 * 一级排序值
	 * @return
	 */
	public Object getFirstOrder();
	
	
	/**
	 * 二级排序值
	 * @return
	 */
	public Object getSecondOrder();
	
}
