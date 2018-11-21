package fatiny.myTool.sortMap.newer;

/**
 * @author Jeremy Feng
 * @param <K>
 *
 */
public interface ISorter {
	
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
