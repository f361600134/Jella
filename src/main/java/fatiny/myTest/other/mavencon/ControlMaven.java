package fatiny.myTest.other.mavencon;

import java.io.File;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;

/**
 * Several ways for packing the files.
 * @author Jeremy Feng
 */
public class ControlMaven {
	
	public static void main(String[] args) {
		String scriptPath = "F:\\fatiny\\HelloMaven";
//		runMavenCmd(scriptPath);
//		runMavenScript(scriptPath);
		runMavenPom(scriptPath);
	}
	
	/**
	 * Package the output files by running the <b>following command</b>.
	 * <p>
	 * <code>cmd /c mvn clean package</code>
	 * @param scriptPath The location of the <b>POM.xml</b>
	 */
	public static void runMavenCmd(String scriptPath){
		String cmd = "cmd /c mvn clean package";
		ControlCmd.runCmd(cmd, null, scriptPath);
	}
	
	/**
	 * Package the output files by running the <b>script file</b>.
	 * <p>
	 * <code>cmd /c start deploy.bat</code>
	 * @param scriptPath The location of the <b>xxx.bat</b>.
	 */
	public static void runMavenScript(String scriptPath){
		String cmd = "cmd /c start deploy.bat";
		//String cmd = "cmd /c start F:\\fatiny\\HelloMaven\\deploy.bat";
		ControlCmd.runCmd(cmd, null, scriptPath);
	}
	
	/**
	 * Package the output files by <b>MavenKit</b>.
	 * @param pathname This can be either an absolute path or a file location.
	 */
	public static void runMavenPom(String pathname){
		try {
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File(pathname));
			request.setGoals(Arrays.asList("clean", "package"));
			Invoker invoker = new DefaultInvoker();
			InvocationResult result = invoker.execute(request);
			if (result.getExitCode() != 0) {
				throw new IllegalStateException( "Build failed." );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
