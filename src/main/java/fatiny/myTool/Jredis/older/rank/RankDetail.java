package fatiny.myTool.Jredis.older.rank;

import java.util.HashMap;
import java.util.Map;

/**
 * 排行榜配置细节
 * @author Administrator
 */
public class RankDetail {
	
	/**
	 * 当前最小分数
	 */
	private double minScore;
	
	/**
	 * 排行榜最大数量
	 */
	private int maximum;
	
	/**
	 * 用于存放缓存数据
	 */
	private Map<String, ISorter> map;
	
	/*** 默认初始容量,-1表示无限制*/
    private static final short DEFAULT_CAPACITY = -1;

    public RankDetail() {
		this.minScore = 0;
		this.maximum = DEFAULT_CAPACITY;
		this.map = new HashMap<String, ISorter>();
	}
	
	public RankDetail(int maximum) {
		this.minScore = 0;
		this.maximum = maximum;
		this.map = new HashMap<String, ISorter>();
	}
	
	public int getMaximum() {
		return maximum;
	}

	public double getMinScore() {
		return minScore;
	}

	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}
	

	/**
	 * 配置排行榜
	 * @param maximum: 最大排行榜
	 * @param 最小上榜值
	 */
	public void colloc(int maximum){
		colloc(maximum, 0);
	}
	
	/**
	 * 配置排行榜
	 * @param maximum: 最大排行榜
	 * @param 最小上榜值
	 */
	public void colloc(int maximum, double minScore){
		setMaximum(maximum);
		setMinScore(minScore);
	}
	
	/**
	 * 设置排行榜最大量
	 * @param maximum
	 */
	public void setMaximum(int maximum) {
		if (maximum == 0) {
			throw new IllegalArgumentException("the input parameter {maximum} is not allowed be zero");
		}
		this.maximum = maximum;
	}
	
	public void put(ISorter sorter){
		map.put(sorter.getSortId(), sorter);
	}
	
	public void putAll(Map<String, ISorter> sorters){
		//优化了旧方法
		map.putAll(sorters);
	}
	
	public void remove(String key){
		map.remove(key);
	}
	
	public int size(){
		return map.size();
	}
	
	/**
	 * 是否满了
	 * @return true: 满了; false: 没满
	 */
	public boolean isFull(){
		return maximum > DEFAULT_CAPACITY && this.size() == maximum;
	}
	
	/**
	 * 是否满了
	 * @return true: 超出了; false: 没超出
	 */
	public boolean isFlow(){
		return maximum > DEFAULT_CAPACITY && this.size() > maximum;
	}
	
	/**
	 * 获取溢出数量
	 * @return
	 */
	public int getSpillSize(){
		int result = this.size() - maximum;
		if (result < 0)return 0;
		return result;
	}
	
	public ISorter get(String key){
		return map.get(key);
	}
	
	public Map<String, ISorter> getMap(){
		return map;
	}
	
}















