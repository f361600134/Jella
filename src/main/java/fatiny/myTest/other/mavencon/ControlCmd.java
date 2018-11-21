package fatiny.myTest.other.mavencon;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * Several ways for running the commands.
 * @author Jeremy Feng
 */
public class ControlCmd {
	
	private static final String SUCCESS	= "EXECUTED SUCCESSFULLY.";
	private static final String FAILED	= "EXECUTED FAILED.";
	
	/**
	 * Used to run the commands.
	 * @param cmd 	a specified system command.
	 * @param envp 	your environment values such as Java_home and m2_home. 
	 * @param workstation 	the working directory of the current process.
	 */
	public static void runCmd(String cmd, String[] envp, File workstation) {
		String line = null;
		InputStream stdout = null, stderr = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd, envp, workstation);
			stdout = proc.getInputStream();
			stderr = proc.getErrorStream();
			line = getResult(stdout);
		} catch (Exception e) {
			line = getResult(stderr);
		}
		System.out.println(line);
	}
	
	public static void runCmd(String[] cmd, String[] envp, File workstation) {
		String line = null;
		InputStream stdout = null, stderr = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd, envp, workstation);
			stdout = proc.getInputStream();
			stderr = proc.getErrorStream();
			line = getResult(stdout);
		} catch (Exception e) {
			line = getResult(stderr);
		}
		System.out.println(line);
	}
	
	public static void runCmd(String cmd, String[] envp, String workpath) {
		runCmd(cmd, envp, new File(workpath));
	}
	
	/**
	 * Return the result after this method executed
	 * <p>
	 * the result contains runtime information and error logs.
	 * @param stdout
	 * @return
	 */
	private static String getResult(InputStream stdout){
		String result = null;
		try {
			InputStreamReader isr = new InputStreamReader(stdout,"gbk");
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
	        while ((line = br.readLine()) != null) {  
	            sb.append(line + "\n");
	        }
	        result = sb.toString();
	        result = appendCode(result, SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = appendCode(result, FAILED);
		}
		return result;
	}
	
	private static String appendCode(String result, String errorCode){
		if (result == null || result.equals(""))
       	 	result = errorCode;
		else
			result = result+ "\n" + errorCode;
		return result;
	}

	/*
	 * cmd /c dir 是执行完dir命令后关闭命令窗口。 
	 * cmd /k dir 是执行完dir命令后不关闭命令窗口。 cmd /c start
	 * dir 会打开一个新窗口后执行dir指令，原窗口会关闭。 
	 * cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
	 * 可以用cmd /?查看帮助信息。
	 */
	public static void runCmd(String cmd) {
		String line = null;
		InputStream stdout = null, stderr = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			stdout = proc.getInputStream();
			stderr = proc.getErrorStream();
			line = getResult(stdout);
		} catch (Exception e) {
			line = getResult(stderr);
		}
		System.out.println(line);
	}
	
	@Test
	public void test(){
		String cmd = "cmd /c mkdir aaa";
		String path = "F:/fatiny/HelloMaven";
		runCmd(cmd, null, path);
	}

}
