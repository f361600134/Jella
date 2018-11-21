package fatiny.myTool.sortMap.older;

/**
 * @author Jeremy Feng
 * @param <K>
 *
 */
public interface IRanker {
	
	/**
	 * 对象唯一id
	 * @return
	 */
	public Object getId();
	
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
