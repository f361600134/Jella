package fatiny.myTest.masterworker.demo;

import java.util.ArrayList;
import java.util.Collection;

import fatiny.myTest.masterworker.demo.impl.ComputeWorker;
import fatiny.myTest.masterworker.demo.impl.IComputeBean;
import fatiny.myTest.masterworker.demo.impl.IComputeMaster;

public class Calculate extends ComputeWorker{

	
	public Calculate(Collection<IComputeBean> beans) {
		super(beans);
	}

	@Override
	public Collection<IComputeBean> split() {
		
		 Collection<IComputeMaster> jobs = new ArrayList<>();
		 Collection<IComputeBean> employees = new ArrayList<>();
		
		 for (IComputeBean employee : beans) {
	            employees.add(employee);

//	            if (employees.size() == 3) {
//	                jobs.add(new ComputeClientJob(employees));
//
//	                employees = new ArrayList<>(3);
//	            }
	      }
		 return null;
	}

	@Override
	public void execute() {
		
	}

}
