package fatiny.myTool.sortMap.newer1;

import java.util.Comparator;

import fatiny.myTool.sortMap.newer1.ISorter;

/**
 * @author Jeremy Feng
 *
 */
public class RankComparator implements Comparator<ISorter<Long>>{
	/**
	 * 降序排序
	 * o1<o2 : 返回1
	 * o1>o2 : 返回-1
	 * o1=o2 : 返回0
	 */
	@Override
	public int compare(ISorter<Long> o1, ISorter<Long> o2) {
		//正常返回值是升序
		int code = Integer.compare((Integer)o1.getFirstOrder(), (Integer)o2.getFirstOrder());
		if (code == 0) {
			//排名相同,时间早排名靠前
			return Long.compare((Long)o1.getSecondOrder(), (Long)o2.getSecondOrder());
		}
		//设置成降序
		return -code;
	}
	
} 
