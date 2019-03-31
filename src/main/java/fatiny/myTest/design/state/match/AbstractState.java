package fatiny.myTest.design.state.match;

import java.util.Date;

public abstract class AbstractState implements IState{
	
	private int code; //状态码
	private Match match; //比赛
	
	private Date startTime;
	private Date endTime;
	
//	public AbstractState() {
//	}
	
	public AbstractState(int code, Match match) {
		super();
		this.code = code;
		this.match = match;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
