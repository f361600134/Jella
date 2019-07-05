package fatiny.myTest.hotswap;

import sun.management.ManagementFactoryHelper;

public class JvmUtil {

	/**
	 * 获取jvm进程id
	 * @return  
	 * @return int  
	 * @date 2019年7月3日上午10:10:25
	 */
	public static int getPid() {
		String name = ManagementFactoryHelper.getRuntimeMXBean().getName();
		int index = name.indexOf("@");
		if (index > 0) {
			name = name.substring(0,index);
			return Integer.parseInt(name);
		}
		return 0;
	}
	
}
