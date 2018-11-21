package fatiny.myTool.scp.util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException; 

/**
 * 解析XML工具类
 * @author lc
 *
 */
public class XMLUtils {
	
	/**
	 * 传入xml文件的路径，返回XML文档的根节点element
	 * 
	 * @param path
	 *            xml文件的路径
	 * @return element
	 */
	public static Element getElementMyXML(String path) {
		Element element = null;
		File file = new File(path);
		if(!file.exists()){//文件不存在
			return null;
		}
		
		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;

		try {
			// 得到DOM解析器的工厂实例
			// 得到javax.xml.parsers.DocumentBuilderFactory；类的实例就是我们要的解析器工厂
			dbf = DocumentBuilderFactory.newInstance();
			// 从DOM工厂获得DOM解析器
			// 通过javax.xml.parsers.DocumentBuilderFactory实例的静态方法newDocumentBuilder（）得到DOM解析器
			db = dbf.newDocumentBuilder();
			// 得到一个DOM并返回给document对象
			Document dt = db.parse(file);
			// 得到XML文档的根节点
			// 在DOM中只有根节点是一个org.w3c.dom.Element对象。
			element = dt.getDocumentElement();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * 传入父节点fatherNode，返回所有子节点childNode放入ArrayList中
	 * 
	 * @param element
	 * @return ArrayList
	 */
	public static ArrayList<Node> getAllChildNodesMyXML(NodeList fatherNode) {
		ArrayList<Node> al = new ArrayList<Node>();
		// 遍历所有节点
		for (int i = 0; i < fatherNode.getLength(); i++) {
			Node childNode = fatherNode.item(i);
			// 如果子节点为空跳过
			if ("#text".equals(childNode.getNodeName())) {
				continue;
			}
			// 子节点有内容，加入到arraylist中
			al.add(childNode);
		}
		return al;
	}

	/**
	 * 传入父节点fatherNode，和字符串name，返回子节点名字等于name的childNode放入ArrayList中
	 * 
	 * @param element
	 * @param name
	 * @return ArrayList
	 */
	public static ArrayList<Node> getChildNodesMyXML(NodeList fatherNode, String name) {
		ArrayList<Node> al = new ArrayList<Node>();
		// 遍历所有节点
		for (int i = 0; i < fatherNode.getLength(); i++) {
			Node childNode = fatherNode.item(i);
			// 如果子节点的名字等于name
			if (name.equals(childNode.getNodeName())) {
				// 加入到arraylist中
				al.add(childNode);
			}
		}
		return al;
	}

	/**
	 * 传入父节点fatherNode，和字符串name，返回子节点属性attributes等于name的childNode放入ArrayList中
	 * 
	 * @param fatherNode
	 * @param attributes
	 * @param name
	 * @return
	 */
	public static ArrayList<Node> getChildNodesMyXMLAttributes(NodeList fatherNode,
			String attributes, String name) {
		ArrayList<Node> al = new ArrayList<Node>();
		// 遍历所有节点
		for (int i = 0; i < fatherNode.getLength(); i++) {
			Node childNode = fatherNode.item(i);
			System.out.println(childNode.getNodeName());
			try {
				// 如果子节点的属性attributes等于name，则加入到arraylist中
				if (name.equals(childNode.getAttributes()
						.getNamedItem(attributes).getNodeValue())) {
					al.add(childNode);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return al;
	}
	
	public static void main(String[] args) {  
        //声明XML地址，不填写路径的话默认为工程文件夹根目录下  
        String path = "config/db/db-info.xml";
        //获得Element对象  
        Element element = getElementMyXML(path);
        
        NodeList fatherNode = element.getChildNodes();
        
        ArrayList<Node> arrayList = getAllChildNodesMyXML(fatherNode);
        for (int i = 0; i < arrayList.size(); i++) {
        	ArrayList<Node> arrayList2 = getAllChildNodesMyXML(arrayList.get(i).getChildNodes());
        	if (!arrayList2.isEmpty()) {
				System.out.println(arrayList.get(i).getNodeName() + "--->");
			}
        	for (int j = 0; j < arrayList2.size(); j++) {
        		NamedNodeMap nodeMap = arrayList2.get(j).getAttributes();
        		System.out.print("\t");
				System.out.print(nodeMap.getNamedItem("key").getNodeValue() + ":");
				System.out.println(nodeMap.getNamedItem("value").getNodeValue());
			}
		}
	}	

}






