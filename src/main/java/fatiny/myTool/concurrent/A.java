package fatiny.myTool.concurrent;

public final class A {
	
	private static int id;
	private static String name;
	
	public static void print(){
		System.out.println(id+", "+name);
	}
	
	public static void main(String[] args) {
		A.id = 1;
		A.name = "a";
		
		A.print();
		
		A.id = 2;
		A.name = "b";
		A.print();
	}
	
}
