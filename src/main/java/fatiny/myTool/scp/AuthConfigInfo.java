package fatiny.myTool.scp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fatiny.myTool.scp.util.XMLUtils;

public class AuthConfigInfo {
	
	private static final String PATH = "res/config/auth-mapping.xml";
	private static AuthConfigInfo authConfig = new AuthConfigInfo();
	
	public static AuthConfigInfo instance() {
		return authConfig;
	}
	
	/** {数据服标识=数据服配置} */
	private Map<Integer, Properties> configs = new HashMap<>();
	
	public void loadConfig() {
		Element element = XMLUtils.getElementMyXML(PATH);
        NodeList fatherNodes = element.getChildNodes();		// 父节点<dbConfig>
		List<Node> childNodes1 = XMLUtils.getAllChildNodesMyXML(fatherNodes); // 一级子节点
        for (int i = 0; i < childNodes1.size(); i++) {
        	Node node = childNodes1.get(i);
			List<Node> childNodes2 = XMLUtils.getAllChildNodesMyXML(node.getChildNodes()); // 二级子节点
        	if (childNodes2.isEmpty()) {
        		continue;
			}
        	//解析公共数据
        	Properties p = new Properties();
        	for (int j = 0; j < childNodes2.size(); j++) {
        		NamedNodeMap nodeMap = childNodes2.get(j).getAttributes();
        		if (nodeMap == null) {
					continue;
				}
        		p.setProperty(nodeMap.getNamedItem("key").getNodeValue(), 
        				nodeMap.getNamedItem("value").getNodeValue());
			}
        	//解析服务器
        	List<Node> childNodes3 = XMLUtils.getAllChildNodesMyXML(node.getChildNodes()); // 二级子节点
        	Properties p3 = new Properties();
        	for (int j = 0; j < childNodes3.size(); j++) {
        		NamedNodeMap nodeMap = childNodes2.get(j).getAttributes();
        		if (nodeMap == null) {
					continue;
				}
        		p.setProperty(nodeMap.getNamedItem("key").getNodeValue(), 
        				nodeMap.getNamedItem("value").getNodeValue());
			}
        	int serverId = Integer.parseInt(p.getProperty("serverId"));
        	configs.put(serverId, p);
		}
	}
	
	
	
	public String getIp(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return config.getProperty("ip");
	}
	
	public int getPort(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return Integer.parseInt(config.getProperty("port"));
	}
	
	public String getUsername(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return config.getProperty("username");
	}
	
	public String getPassword(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return config.getProperty("password");
	}
	
	public String getSource(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return config.getProperty("source");
	}
	
	public String getTarget(int dbServerId) {
		Properties config = configs.get(dbServerId);
		return config.getProperty("target");
	}
	
	public List<Integer> allServerIds() {
		return new ArrayList<>(configs.keySet());
	}
	
	public static void main(String[] args) {
		authConfig.loadConfig();
		System.out.println(authConfig.getIp(1));
	}

}
