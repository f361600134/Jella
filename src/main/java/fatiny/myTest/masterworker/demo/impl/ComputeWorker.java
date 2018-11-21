package fatiny.myTest.masterworker.demo.impl;

import java.util.Collection;

public abstract class ComputeWorker implements IComputeWorker{
	
	public Collection<IComputeBean> beans;
	
	public ComputeWorker(Collection<IComputeBean> beans){
		this.beans = beans;
	}
	
	public void run(){
		split();
		run();
	}

}
