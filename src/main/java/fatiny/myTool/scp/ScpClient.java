package fatiny.myTool.scp;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.ethz.ssh2.Connection;
import fatiny.myTool.scp.base.AuthBase;
import fatiny.myTool.scp.util.ScpLog;

/**
 * @author Administrator
 * 上传-执行命令-下载 为一个生命周期
 * 上传: 上传指定文件到远程目录
 * 运行命令: 
 * 	关闭服务器: 停服之后, 能检测有没有正常的关闭服务器(sh stop.sh y)
 *  解压文件: 解压当前文件(unzip -o filename)
 *  拷贝覆盖文件: 拷贝文件到指定的目录下 (mv -bf filename)
 *  开启服务器: (sh runner.sh)
 *  --检索错误:开服后, 日志中检索异常信息, 如果存在异常, 返回记录
 * 下载: 下载异常服务器的错误日志, 用于分析
 */
public class ScpClient {
	
	//本地解析文件
	private static final String XMLPATH = "res/config/auth-mapping.xml";
	//缓存验证信息
	private CopyOnWriteArrayList<AuthBase> authInfos = new CopyOnWriteArrayList<AuthBase>();
//	private Map<String, AuthBase> authInfoMap = new ConcurrentHashMap<String, AuthBase>();
	//上传文件信息
	private File loadFile;
	
	
	public static ScpClient create(){
		ScpClient client = new ScpClient();
		client.loadConfig();
		return client;
	}
	
	/**
	 * 初始化配置信息
	 */
	public void loadConfig(){
        try {  
        	File f = new File(XMLPATH);  
            org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();  
            org.dom4j.Document doc = reader.read(f);  
            org.dom4j.Element root = doc.getRootElement();  
            org.dom4j.Element foo;
            for (Iterator<?> i = root.elementIterator("common"); i.hasNext();) {
                 foo = (org.dom4j.Element) i.next();
                 String path = foo.elementText("source");
                 loadFile = new File(path);
                 if (!loadFile.isFile()) {
					ScpLog.error("上传文件出错, path:{}", path);
					System.exit(0);
				}
			}
            for (Iterator<?> i = root.elementIterator("server"); i.hasNext();) {
            	foo = (org.dom4j.Element) i.next(); 
            	String serverId = foo.elementText("serverId");
            	String ip = foo.elementText("ip");
    			int port = Integer.parseInt(foo.elementText("port"));
    			String username = foo.elementText("username");
    			String password = foo.elementText("password");
    			String target = foo.elementText("target");
    			AuthBase authInfo = new AuthBase(serverId, ip, port, username, password, target);
    			authInfos.add(authInfo);
			}
        } catch (Exception e) {  
        	ScpLog.error("初始化配置信息出错", e);
        }  
	}
	/**
	 * 获取到服务器信息
	 * @param serverId
	 * @return
	 */
	AuthBase getServerInfo(String serverId) {
		for (AuthBase auth : authInfos) {
			if (auth.getServerId().equals(serverId)) {
				return auth;
			}
		}
		return null;
	}
	
	private Connection getConnection(String serverId){
		AuthBase auth = getServerInfo(serverId);
		Connection conn = new Connection(auth.getIp(), auth.getPort());
		try {
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(auth.getUsername(), auth.getPassword());
			if (isAuthenticated == false) {
				ScpLog.error("authentication failed");
			    return null;
			}
			return conn;
		} catch (IOException e) {
			ScpLog.error("authentication failed.", e);
		}
		return null;
	}
	
//	Map<Integer, String> resultMap = new ConcurrentHashMap<Integer, String>();
//	public void putFile(){
//		Connection conn = null;
//		for (AuthBase authInfo : authInfos) {
//			conn = getConnection(authInfo.getServerId());
//			ScpAssist.putFile(conn, authInfo.getSource(), authInfo.getTarget());
//		}
//	}
	
	/**
	 * 开始执行任务
	 * 0. 关闭服务器
	 * 1. 进入目录 cd /home/Jeremy
	 * 2. 上传文件 
	 * 3. 解压文件
	 * 4. 复制替换文件
	 * 5. 启动服务器
	 */
	Map<Integer, String> resultMap = new ConcurrentHashMap<Integer, String>();
	public void startWork(){
		print();
		
		Connection conn = null;
		for (AuthBase authInfo : authInfos) {
			conn = getConnection(authInfo.getServerId());
//			ScpAssist.runCommand(conn, "cd /home/Jeremy");
			ScpAssist.putFile(conn, loadFile.getAbsolutePath(), "/home/Jeremy/");
			ScpAssist.runCommand(conn, "cp /home/Jeremy/morningGlory_sample.zip /home/Jeremy/gameserver/morningGlory_s1/ ");
			ScpAssist.runCommand(conn, "unzip -o /home/Jeremy/gameserver/morningGlory_s1/morningGlory_sample.zip");
//			ScpAssist.putFile(conn, authInfo.getSource(), authInfo.getTarget());
//			ScpAssist.runCommand(conn, "cp morningGlory_sample.zip /home/Jeremy/gameserver/morningGlory_s1/");
//			ScpAssist.runCommand(conn, "unzip -o morningGlory_sample.zip");
//			ScpAssist.runCommand(conn, "ls");
			conn.close();
		}
	}
	
	public void print(){
		System.out.println("File getAbsolutePath:"+loadFile.getAbsolutePath());
		System.out.println("File getName:"+loadFile.getName());
	}
	
	
	
	
	
}
