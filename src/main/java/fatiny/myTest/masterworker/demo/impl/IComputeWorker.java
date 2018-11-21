package fatiny.myTest.masterworker.demo.impl;

import java.util.Collection;

public interface IComputeWorker {
	
	public Collection<IComputeBean> split();
	
	public void execute();

}
