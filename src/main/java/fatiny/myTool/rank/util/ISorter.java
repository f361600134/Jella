package fatiny.myTool.rank.util;

/**
 * @author Jeremy Feng
 * 如果想使用通用工具,就要实现这个接口
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
