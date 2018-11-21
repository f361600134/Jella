package fatiny.myTest.design.template.operation;

/**
 * @author Jeremy Feng
 */
public interface IHandler {
	
	/**错误*/
	int ERROR = -1;
	/**未完成*/
	int NONE = 0;
	/**已完成未领奖*/
	int DONE = 1;
	/**已领奖*/
	int REWARDED = 2;
	
	public void refresh();
	
	public void reward(int ...param);
	
	public void onTrigger(int ...param);
	
	public int checkOpen(String active);
	
	public void reset();
	
}
