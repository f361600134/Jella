package fatiny.myTool.other;

import java.io.File;
import java.util.TreeMap;

/**
 * @author Jeremy Feng
 *
 */
public class test {
	
	 public static void main(String[] args) {
		 treeMap();
	 }
	 
	 
	 public void serviceLoader(){
		 try {

	         // print a message
	         System.out.println("Executing notepad.exe...");

	         // create a file with the working directory we wish
	         File dir = new File("F:/fatiny/HelloMaven");

	         // create a process and execute notepad.exe and currect environment
	         Process process = Runtime.getRuntime().exec("cmd /c mkdir aaa", null, dir);
//	         Process process = Runtime.getRuntime().exec("cmd /c start deploy.bat", null, dir);
	         // print another message
	         System.out.println("Notepad should now open.");

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	 }
	 
	 public static void treeMap(){
		 TreeMap<Integer, Integer> map = new TreeMap<>();
		 map.put(19, 19);
		 map.put(39, 39);
		 map.put(59, 59);
//		 System.out.println(map.floorEntry(18).getValue());
		 System.out.println(map.floorEntry(18));
		 System.out.println(map.ceilingEntry(59).getValue());
//		 System.out.println(map.higherEntry(59).getValue());
	 }

}
