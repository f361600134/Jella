package fatiny.myTest.other.other;

/**
 * @author Jeremy Feng
 * final class 只是针对对象不能修改
 * final 字段 字段才不能修改
 */
public class testFinal {

	public static void main(String[] args) {
		
		Student student = new Student(1, "a");
		System.out.println("Before:"+student);
		
		changeA(student);
		System.out.println("changeA:"+student);
		
		changeB(student);
		System.out.println("changeB:"+student);
		
	}
	
	static void changeA(Student student){
		student = new Student(111, "aaa");
	}
	
	static void changeB(Student student){
		student.setId(2);
		student.setName("b");
	}
	
	public static final class Student {
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Student(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + "]";
		}

	}
}
