package fatiny.myTool.Jredis.newer.rank;

/**
 * 加强版, 对接口使用了泛型.
 * @author Jeremy Feng
 * @param <K>
 */
public interface ISorter {
	
	/**
	 * 对象唯一id
	 * @return
	 */
	public String getSortId();
	
	/**
	 * 一级排序值
	 * @return
	 */
	public double getFirstOrder();
	
	
	/**
	 * 二级排序值
	 * @return
	 */
	public Object getSecondOrder();
	
}
