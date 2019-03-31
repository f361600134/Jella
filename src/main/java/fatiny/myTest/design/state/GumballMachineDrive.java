package fatiny.myTest.design.state;

public class GumballMachineDrive {

	public static void main(String[] args) {
		GumballMachine gumbalMachine = new GumballMachine(2);

		System.out.print(gumbalMachine + "\n");

		gumbalMachine.insertQuarter();
		gumbalMachine.turnCrank();

		System.out.print(gumbalMachine + "\n");

		gumbalMachine.insertQuarter();
		gumbalMachine.turnCrank();

		System.out.print(gumbalMachine + "\n");

		gumbalMachine.insertQuarter();
		gumbalMachine.turnCrank();

		System.out.print(gumbalMachine + "\n");

	}

}
