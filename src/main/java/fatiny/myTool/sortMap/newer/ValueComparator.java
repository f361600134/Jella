package fatiny.myTool.sortMap.newer;

import java.util.Comparator;

/**
 * @author Jeremy Feng
 *
 */
public class ValueComparator implements Comparator<RankInfo>{

	@Override
	public int compare(RankInfo o1, RankInfo o2) {
		int code = Integer.compare((Integer)o1.getFirstOrder(), (Integer)o2.getFirstOrder());
		if (code == 0) {
			//排名相同,时间早排名靠前
			return Long.compare((Long)o1.getSecondOrder(), (Long)o2.getSecondOrder());
		}
		//设置成降序
		return -code;
	}
	
}
