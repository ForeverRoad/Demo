package junit;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class TestDom4j {
	@Test
	public void testReadXml(){
		String str = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		Map <String,String> map = new HashedMap();
		SAXReader reader = new SAXReader();
		Document document = null;
	    try {
	    	document = DocumentHelper.parseText(str);
	    	Element root = document.getRootElement();
	    	// 枚举所有子节点
	        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
	           Element element = (Element) i.next();
	           map.put(element.getName(), element.getData().toString());
	           System.out.println(element.getData());
	        }
	       
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testToXml(){
		Map<String, String> map = new HashedMap();
		map.put("a", "a");
		map.put("b", "b");
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		Element e = null;
		for (int i = 0; i < map.size(); i++) {
			
		}
	}
}
