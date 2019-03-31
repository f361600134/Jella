package fatiny.myTest.design.state.match;

import java.util.Map;

import com.google.common.collect.Maps;

public class Match implements IState{
	
	private IState closeState;
	private IState preliminaryState;
	private IState preparState;
	private IState finalState;
	
	private Map<Integer, IState> stateMap;
	
	private IState curState; //根据保存的状态进行设置
	private int saveState = IState.Close;
	
	public Match() {
		closeState = new CloseState(IState.Close, this);
		preliminaryState = new PreliminaryState(IState.Preliminary, this);
		preparState = new PreparState(IState.Prepar, this);
		finalState = new FinalState(IState.Final, this);
		this.stateMap = Maps.newHashMap();
		this.stateMap.put(IState.Close, closeState);
		this.stateMap.put(IState.Preliminary, preliminaryState);
		this.stateMap.put(IState.Prepar, preparState);
		this.stateMap.put(IState.Final, finalState);
		//根据保存的状态设置状态
		this.curState = this.stateMap.get(saveState);
	}
	
	public IState getCloseState() {
		return closeState;
	}

	public IState getPreliminaryState() {
		return preliminaryState;
	}

	public IState getPreparState() {
		return preparState;
	}

	public IState getFinalState() {
		return finalState;
	}

	public int getSaveState() {
		return saveState;
	}
	
	public void setCurState(IState state) {
		this.curState = state;
	}
	
	public IState getCurState() {
		return curState;
	}
	
	int day = 0, hour = 0;
	public int getDay() {
		return day;
	}
	public int getHour() {
		return hour;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}

	
	@Override
	public void close() {
		curState.close();
	}

	@Override
	public void entryPreliminary() {
		curState.entryPreliminary();
	}

	@Override
	public void preparFinal() {
		curState.preparFinal();
	}

	@Override
	public void entryFinal() {
		curState.entryFinal();
	}

	@Override
	public void reward() {
		curState.reward();
	}

}
