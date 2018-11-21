package fatiny.myTest.masterworker.demo;

import fatiny.myTest.masterworker.demo.impl.IComputeBean;

public class Employee implements IComputeBean {
	
	private int id;
	private int salary;
	
	
	public Employee() {
	}
	
	public Employee(int id, int salary) {
		this.id = id;
		this.salary = salary;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public int getValue() {
		return salary;
	}
	
	
	
}
