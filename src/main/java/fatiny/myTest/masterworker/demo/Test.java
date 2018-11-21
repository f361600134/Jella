package fatiny.myTest.masterworker.demo;

import java.util.ArrayList;
import java.util.Collection;

public class Test {
	
	public static void main(String[] args) {
		
		Collection<Employee> employees = employees();
		
		
		
		
		
	}
	
	private static Collection<Employee> employees(){
		
		Collection<Employee> employees = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			employees.add(new Employee(i, i*100));
		}
		
		return employees;
		
	}

}
