package fatiny.myTool.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

/**
 * @author Jeremy Feng
 *
 */
public class controlsvn {

	/*
	 *
	 * cmd /c dir 是执行完dir命令后关闭命令窗口。 
	 * cmd /k dir 是执行完dir命令后不关闭命令窗口。 cmd /c start
	 * dir 会打开一个新窗口后执行dir指令，原窗口会关闭。 
	 * cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
	 * 可以用cmd /?查看帮助信息。
	 */
	public static void runCmd(String cmd) {
		// String cmd = "cmd /c start C://a.txt";
//		String cmd = "cmd /c start www.baidu.com";
		// String cmd = "cmd /?";
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(cmd);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	
	public static void runMavenCmd() {
		// String cmd = "cmd /c start C://a.txt";
		String cmd = "cmd /c start F://fatiny//HelloMaven//deploy.bat";
//		String cmd = "cmd /c call F://fatiny//HelloMaven// mvn clean package";
		// String cmd = "cmd /?";
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(cmd);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	
	
	public static void runCommand(String command){
	    String s="IPv4";  
	    String line = null;  
	    StringBuilder sb = new StringBuilder();  
	    Runtime runtime = Runtime.getRuntime();  
	    try {  
	    Process process = runtime.exec(command);  
	    BufferedReader  bufferedReader = 
	    		new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
	        while ((line = bufferedReader.readLine()) != null) {  
	            sb.append(line + "\n");  
	            if (line.contains(s)) {  
	                System.out.println(line);  
	            }  
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
//	public static int resolveAsCommandLine(){
//	        try {
//	        String command =  "mvn " +
//	                            "-f " + projectPath + "\\pom.xml " +
//	                            "-Dmaven.repo.local=" + localRepoPath + "\\repository " +
//	                            "-Dmaven.test.skip=true " + // ignore tests
//	                            "dependency:resolve " +     // download deps if needed
//	                            "validate";
//
//	            System.out.println("%> Executing command: '" + command + "'...");
//
//	            Process p = Runtime.getRuntime().exec( // "cmd - for windows only"
//	                    "cmd /c " + command
//
//	            );
//
//
//	            BufferedReader in = new BufferedReader(
//	                    new InputStreamReader(p.getInputStream()) );
//
//	            String line = "";
//	            while ((line = in.readLine()) != null) {
//	                System.out.println(line);
//	                if(line.contains("[ERROR]")) return ERROR_STATUS;
//	                if(line.contains("BUILD FAILURE")) return ERROR_STATUS;
//	                if(line.contains("BUILD SUCCESS")) return OK_STATUS;
//	            }
//	            in.close();
//
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return ERROR_STATUS;
//	        }
//	        return ERROR_STATUS;
//	}
	
	public static void main(String[] args) {
//		String command="ipconfig -all";
//		String command="cmd /c start F://fatiny//HelloMaven//deploy.bat";
//		runCommand(command);
		//run3();
		String command = "cmd /c mkdir F:\\fatiny\\HelloMaven\\aaa";
//		runCmd(command);
		cmdMove();
//		run();
	}
	
	public static void run3(){
//		String mavenPath = "D:\\Progs\\springsource\\apache-maven-3.0.4\\bin\\mvn.bat";
//		String mavenOptions  = "-X compile exec:java -Dexec.mainClass=runclass.Runme";
		
		String mavenPath = "F:\\fatiny\\HelloMaven\\deploy.bat";
		String mavenOptions  = "-X compile exec:java -Dexec.mainClass=runclass.Runme";
		
		String[] cmdLine = new String[2];
		cmdLine[0] = mavenPath;  //cmdLine.add(mavenPath);
		cmdLine[1] = mavenOptions;      //cmdLine.add(mavenOptions+" compile exec:java -Dexec.mainClass="+packageClass);        
		
		String[] envp = new String[2];
		//Map<String, String> envm = new HashMap<String, String>();
		envp[0] = "JAVA_HOME=" + System.getProperty("java.home"); //System.getenv("JAVA_HOME");
		envp[1] = "M2_HOME=" + System.getenv("M2_HOME");
		log(envp[0]);
		log(envp[1]);
		
		File workingDirectory = null;
		String currentDir = new File(".").getAbsolutePath();
		log(currentDir);
		String userDir = System.getProperty("user.dir"); //User working directory ; "user.home"     User home directory
		workingDirectory = new File(userDir);       
		log(workingDirectory.toString());
		
		//try
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmdLine, envp, workingDirectory);
			InputStream stdout = proc.getInputStream();
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stdout,"gbk");
			InputStreamReader isr2 = new InputStreamReader(stderr,"gbk");
			
			BufferedReader br = new BufferedReader(isr);
			BufferedReader br2 = new BufferedReader(isr2);
			
			String line = null;  
		    StringBuilder sb = new StringBuilder();  
	        while ((line = br.readLine()) != null) {  
	            sb.append(line + "\n");  
                System.out.println(line);  
	        }
	        while ((line = br2.readLine()) != null) {  
	            sb.append(line + "\n");  
                System.out.println(line);  
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cmdMove(){
//		String disk = "cmd /c mkdir aaa";
		String disk = "cmd /c start deploy.bat";
		String path = "F:/fatiny/HelloMaven";
		
		File workStation = new File(path);
		log(workStation.toString());
		String cmd[] = new String[]{disk};
		try {
			
			String[] envp = new String[2];
			//Map<String, String> envm = new HashMap<String, String>();
			envp[0] = "JAVA_HOME=" + System.getProperty("java.home"); //System.getenv("JAVA_HOME");
			envp[1] = "M2_HOME=" + System.getenv("M2_HOME");
			log(envp[0]);
			log(envp[1]);
			
			
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd, null, workStation);
//			Process proc = rt.exec(command, envp, dir)
		    
		    InputStreamReader isr = new InputStreamReader(proc.getInputStream(),"gbk");
			BufferedReader br = new BufferedReader(isr);
			String line = null;  
		    StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
		        sb.append(line + "\n");
		        System.out.println(line);  
			}
			
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr2 = new InputStreamReader(stderr,"gbk");
			BufferedReader br2 = new BufferedReader(isr2);
			while ((line = br2.readLine()) != null) {  
		        sb.append(line + "\n");  
		        System.out.println(line);  
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void log(String log){
		System.out.println(log);
	}
	
	public static void run(){
		try {
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile( new File( "F:/fatiny/HelloMaven/pom.xml" ) );
			request.setGoals(Arrays.asList( "clean", "package" ) );
			Invoker invoker = new DefaultInvoker();
			InvocationResult result = invoker.execute( request );
			if (result.getExitCode() != 0) {
				throw new IllegalStateException( "Build failed." );
			}
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	}
	

//	public static void runCmdPlus(){
//		MyResult result = new MyResult();
//		/**指令执行结果*/
//		StringBuffer queryInputResult = new StringBuffer(100);
//		/**输出错误的结果*/
//		StringBuffer queryErroInputResult = new StringBuffer(100);
//		try {　　　　　　
//		// 执行相应命令，本质上是JVM中启一个子进程来执行
//		Process pro = Runtime.getRuntime().exec(command);
//		// 等待执行完成<br>pro.waitfor();<span>  </span>
//		   
//		        CountDownLatch lock = new CountDownLatch(2);
//		        // 处理正常的输入流
//		executor.execute(new MyCheckTask(queryInputResult, lock, pro.getInputStream()));
//		// 处理error流
//		executor.execute(new MyCheckTask(queryErroInputResult, lock, pro.getErrorStream()));
//		boolean done = false;
//		while (!done) {
//		    try {
//		        lock.await();
//		        done = true;
//		    } catch (InterruptedException e) {
//		        // loop to wait
//		        }
//		    }
//		    result.setSuccess(true);
//		    if (result.isSuccess()) {
//		        result.setResult(queryInputResult.toString());
//		    }
//		} catch (Exception e) {
//		    e.printStackTrace();
//		    result.setMessage("error:指令：【" + command + "】 执行失败");
//		    result.setEx(e);
//		    result.setSuccess(false);
//		}
//		return result;
//	}

	public static void runSVN() {

	}

	////
	// @SuppressWarnings("static-access")
	// public static void main(String[] args) {
	// SVNCheckOut test = new SVNCheckOut();
	// test.checkOut("svn://192.168.1.235/website/docs/db/", "D:/a", "xiaofeng",
	// "");
	// }
	// // 检出文件
	// /**
	// *
	// * @param url svn地址必须是以svn：开头的
	// * @param desPath 导出目录地址
	// * @param username svn用户名
	// * @param password
	// * svn密码
	// */
	// public static void checkOut(String url, String desPath, String username,
	// String password) {
	//
	// SVNClientManager ourClientManager;
	// // 初始化支持svn://协议的库。 必须先执行此操作。
	// SVNRepositoryFactoryImpl.setup();
	// // 相关变量赋值
	// SVNURL repositoryURL = null;
	// try {
	// repositoryURL = SVNURL.parseURIEncoded(url);
	// } catch (SVNException e) {
	// System.out.println("初始化svn地址失败" + url);
	// System.out.println(e);
	// }
	// FSRepositoryFactory.setup();
	// DAVRepositoryFactory.setup();
	// SVNRepositoryFactoryImpl.setup();
	// ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
	// // 实例化客户端管理类
	// ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)
	// options);// 无密码
	// // ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)
	// // options,username,password);
	// // 要把版本库的内容check out到的目录
	// File desFile = new File(desPath);
	// // 通过客户端管理类获得updateClient类的实例。
	// SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
	// updateClient.setIgnoreExternals(false);
	// try {
	// // 返回工作版本号
	// long workingVersion = updateClient.doCheckout(repositoryURL, desFile,
	// SVNRevision.HEAD, SVNRevision.HEAD,
	// SVNDepth.INFINITY, true);
	// System.out.println("把版本:" + workingVersion + " check out 到目录：" + desPath
	// + "中");
	// } catch (SVNException e) {
	// System.out.println("检出代码出错:" + e);
	// }
	//
	// }
	////

}
