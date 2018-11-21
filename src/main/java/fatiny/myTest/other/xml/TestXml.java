package fatiny.myTest.other.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;

/**
 * @author Jeremy Feng
 *
 */
public class TestXml {
	
	/** 
    * JDOM方式 
     */  
    public void JDOM() {  
        long lasting = System.currentTimeMillis();  
        try {  
        	org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder();  
            org.jdom2.Document doc = builder.build(new File("res/config/test.xml"));  
            org.jdom2.Element foo = doc.getRootElement();  
            List<Element> allChildren = foo.getChildren();  
            for (Element element : allChildren) {
            	System.out.println("ID:"  + (element.getAttribute("id").getValue()));  
				System.out.println("Name:"  + (element.getChild("name").getText()));  
				System.out.println("Space:"   + (element.getChild("space").getText()));  
				System.out.println("-------------------------------------------------");    
			}  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("JDOM RUNTIME："  
                + (System.currentTimeMillis() - lasting) + " MS");  
    }  
	
	
	 /** 
     * DOM4J方式 
     */  
    public void DOM4J() {  
        long lasting = System.currentTimeMillis();  
        try {  
            File f = new File("res/config/test.xml");  
            org.dom4j.io.SAXReader reader = new org.dom4j.io.SAXReader();  
            org.dom4j.Document doc = reader.read(f);  
            org.dom4j.Element root = doc.getRootElement();  
            org.dom4j.Element foo;
            
            for (Iterator i = root.elementIterator("node"); i.hasNext();) {  
                foo = (org.dom4j.Element) i.next(); 
                System.out.println("ID:" + foo.attributeValue("id"));
                System.out.println("Name:" + foo.elementText("name"));  
                System.out.println("Space:" + foo.elementText("space"));  
                System.out.println("-------------------------------------------------");  
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("DOM4J RUNTIME："   + (System.currentTimeMillis() - lasting) + " MS");  
    }
    
    /**
     * xml解析两种方式
     * @param args
     */
    public static void main(String[] args) {
		TestXml myXML = new TestXml();
		myXML.DOM4J();  
		System.out.println("=================================================");  
		
		myXML.JDOM();
		System.out.println("================================================="); 
		
	}
}
