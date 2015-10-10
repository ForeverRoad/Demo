package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.faces.renderkit.html_basic.HiddenRenderer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zcf.bean.Article;
import com.zcf.bean.RespTextMessage;


public class MessageUtil {
	
	//从request中获取数据
	public static String readLine(HttpServletRequest request){
		InputStream in = null;
		String str = null;
		int len=0;

		byte[]buffer=new byte[1024];
		 
	    try {
	    	in = request.getInputStream();
	    	while((len=in.read(buffer))>0){
	    		str=new String(buffer,0,len);
	   		}
	    }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	    return str;
			
	}
	
	public static Map<String, String> parseXml(HttpServletRequest request) {
		
		Map <String,String> map = new HashedMap();
		SAXReader reader = new SAXReader();
		Document document = null;
		
		String str = readLine(request);
		System.out.println("str----------------"+str);
	    try {
	    	//获取xml解析对象
	    	document = DocumentHelper.parseText(str);
	    	//document = reader.read(in);
	    	//获取根节点
	    	Element root = document.getRootElement();
	    	// 枚举所有子节点
	        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
	           Element element = (Element) i.next();
	           //把xml的节点名和内容放入map中
	           
	           map.put(element.getName(), element.getData().toString());
	           
	        }
	       
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
public static Map<String, String> parseXml(String str) {
		
		Map <String,String> map = new HashedMap();
		SAXReader reader = new SAXReader();
		Document document = null;
		
	    try {
	    	//获取xml解析对象
	    	document = DocumentHelper.parseText(str);
	    	//document = reader.read(in);
	    	//获取根节点
	    	Element root = document.getRootElement();
	    	// 枚举所有子节点
	        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
	           Element element = (Element) i.next();
	           //把xml的节点名和内容放入map中
	           
	           map.put(element.getName(), element.getData().toString());
	           
	        }
	       
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	public static String toXml(Object o) {
        xStream.autodetectAnnotations(true);
        String className = o.getClass().getSimpleName();
        xStream.alias("xml", o.getClass());
        if("RespNewsMessage".equals(className)){
        	xStream.alias("item",Article.class);
        }
        
        String xml = xStream.toXML(o);  
		
		return xml;
	}
	//重写方法，用于在xml中写特殊字符< [
	public static XStream xStream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter creaStreamWriter(Writer out){
			return new PrettyPrintWriter(out){
				boolean cdata = true;
				protected void witeText(QuickWriter writer,String text){
					if(cdata){
						writer.write("<!CDATA[");
						writer.write(text);
						writer.write("]]");
					}else{
						writer.write("");
					}
				}
			};
			
		}
	});
}
