package fatiny.myTool.Jredis.newer.rank;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 排行榜配置细节
 * @author Administrator
 */
public class RankDetail {
	
	/*** 当前最小分数 */
	private double minScore;
	
	/*** 排行榜最大数量 */
	private long maximum;
	
	/** * 用于保存当前长度*/
	private AtomicLong curSize;
	
	/*** 默认初始容量,-1表示无限制*/
    private static final short DEFAULT_CAPACITY = -1;

    public RankDetail() {
		this.minScore = 0;
		this.maximum = DEFAULT_CAPACITY;
		this.curSize = new AtomicLong();
	}
	
	public RankDetail(int maximum) {
		this.minScore = 0;
		this.maximum = maximum;
		this.curSize = new AtomicLong();
	}
	
	public long getMaximum() {
		return maximum;
	}

	public double getMinScore() {
		return minScore;
	}

	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}
	
	public AtomicLong getCurSize() {
		return curSize;
	}

	public void setCurSize(AtomicLong curSize) {
		this.curSize = curSize;
	}

//	/**
//	 * 配置排行榜
//	 * @param maximum: 最大排行榜
//	 * @param 最小上榜值
//	 */
//	public void colloc(int maximum){
//		colloc(maximum, 0);
//	}
//	
//	/**
//	 * 配置排行榜
//	 * @param maximum: 最大排行榜
//	 * @param 最小上榜值
//	 */
//	public void colloc(int maximum, double minScore){
//		setMaximum(maximum);
//		setMinScore(minScore);
//	}
	
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
	
	/**
	 * 是否满了
	 * @return true: 满了; false: 没满
	 */
	public boolean isFull(){
		return maximum > DEFAULT_CAPACITY && size() == maximum;
	}
	
	/**
	 * 是否满了
	 * @return true: 超出了; false: 没超出
	 */
	public boolean isFlow(){
		return maximum > DEFAULT_CAPACITY && size() > maximum;
	}
	
	/**
	 * 获取溢出数量
	 * @return
	 */
	public long getSpillSize(){
		long result = size() - maximum;
		if (result < 0)return 0;
		return result;
	}
	
	public long size(){
		return curSize.get();
	}
	
	public long increment(){
		return curSize.incrementAndGet();
	}
	
	public long decrement(){
		return curSize.decrementAndGet();
	}
	
	public long addAndGet(long v){
		return curSize.addAndGet(v);
	}
	
	@Override
	public String toString() {
		return "RankDetail [minScore=" + minScore + ", maximum=" + maximum + ", curSize=" + curSize.get() + "]";
	}
}