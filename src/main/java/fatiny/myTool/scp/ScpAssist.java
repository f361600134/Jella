package fatiny.myTool.scp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import fatiny.myTool.scp.util.ScpLog;

public class ScpAssist {
	
	//解压文件也可以解压到指定目录
	//unzip -o xxx.zip -d targetDir
	private static String unzip = "unzip -o morningGlory_sample.zip";
	private static String stop = "sh stop.sh y";
	private static String mv = "mv -bf ";
//	private static String cdDir = "cd ";
	
	
	public static void getFileBatch(Connection conn, String remoteFile, String localTargetDirectory) {
		SCPClient client = null;
		try {
			client = new SCPClient(conn);
			client.get(remoteFile, localTargetDirectory);
		} catch (IOException ex) {
		    Logger.getLogger(SCPClient.class.getName()).log(Level.SEVERE, null,ex);
		}
	}
	
	/**
	 * 上传单文件
	 * @param localFile 本地文件
	 * @param remoteTargetDirectory 远程目标文件夹
	 */
	public static void putFile(Connection conn, String localFile, String remoteTargetDirectory) {
	    SCPClient client = null;
    	try {
    		client = new SCPClient(conn);
	        client.put(localFile, remoteTargetDirectory);
	    } catch (IOException ex) {
	        Logger.getLogger(SCPClient.class.getName()).log(Level.SEVERE, null,ex);
	    }
	}
	
	/**
     * 执行简单命令, 简单作为测试, 一般来说不建议使用这个方法.
     * 官网API介绍, execCommand 有可能无法获取全局的环境变量
     * 当使用exeCommand出现环境变量问题的时候, 使用startShell替代
     * @param command 多条命令使用 A && B 形式组装
     * @param ips
     * @return
     */
    public static String runCommand(Connection conn, String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Session sess = conn.openSession();
            sess.execCommand(command);
            
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                sb.append(line).append("\n");
            }

            ScpLog.info("{}, result: {}, 返回信息:{}", command, (sess.getExitStatus() == 0 ? "执行成功":"执行失败"), sb.toString());
            br.close();
            sess.close();
        } catch (IOException ex) {
        	ScpLog.error("异常退出", ex);
            System.exit(2);
        }
        return sb.toString();
    }
    
	public List<File> getAllFiles(String localFile){
		List<File> result = new ArrayList<>();
		File directory = new File(localFile);
		if (!directory.isDirectory()) {
			ScpLog.error("It's not a directory, localFile:{}",localFile); 
			return result;
		}
		File [] files = directory.listFiles();
		for (File file: files) {
			if (file.isFile()) {
				result.add(file);
			}
		}
		return result;
	}
	

//  /**
//   * 
//   * @param localFile
//   * 
//   * @param remoteFileName
//   * 
//   * @param remoteTargetDirectory
//   * 
//   * @param mode
//   * 	chmod 600 file – owner can read and write
//		chmod 700 file – owner can read, write and execute
//		chmod 666 file – all can read and write
//		chmod 777 file – all can read, write and execute
//   * 
//   */
//   public void putFile(String localFile, String remoteFileName,String remoteTargetDirectory,String mode) {
//       Connection conn = new Connection(ip,port);
//       try {
//           conn.connect();
//           boolean isAuthenticated = conn.authenticateWithPassword(username,
//                   password);
//           if (isAuthenticated == false) {
//               System.err.println("authentication failed");
//           }
//           SCPClient client = new SCPClient(conn);
//           if((mode == null) || (mode.length() == 0)){
//               mode = "0600";
//           }
//           
//           client.put(localFile, remoteFileName, remoteTargetDirectory, mode);
//          
//           //重命名
//           ch.ethz.ssh2.Session sess = conn.openSession();
//           String tmpPathName = remoteTargetDirectory +File.separator+ remoteFileName;
//           String newPathName = tmpPathName.substring(0, tmpPathName.lastIndexOf("."));
//           sess.execCommand("mv " + remoteFileName + " " + newPathName);//重命名回来
//          
//           conn.close();
//       } catch (IOException ex) {
//           Logger.getLogger(SCPClient.class.getName()).log(Level.SEVERE, null,ex);
//       }
//   }
//   
//   public static byte[] getBytes(String filePath) {
//       byte[] buffer = null;
//       try {
//           File file = new File(filePath);
//           FileInputStream fis = new FileInputStream(file);
//           ByteArrayOutputStream byteArray = new ByteArrayOutputStream(1024*1024);
//           byte[] b = new byte[1024*1024];
//           int i;
//           while ((i = fis.read(b)) != -1) {
//               byteArray.write(b, 0, i);
//           }
//           fis.close();
//           byteArray.close();
//           buffer = byteArray.toByteArray();
//       } catch (FileNotFoundException e) {
//           e.printStackTrace();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//       return buffer;
//   }
  

}
