package junit;

import java.io.Writer;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zcf.bean.RespTextMessage;

public class TestXstream {
	
	@Test
	public void testToXml(){
		RespTextMessage respTextMessage = new RespTextMessage();
		respTextMessage.setContent("]");
		respTextMessage.setCreateTime(22234334);
		//respTextMessage.setFromUserName("sfsfs");
		respTextMessage.setToUserName("<!CDATA[");
		respTextMessage.setMsgType("txt");
		
		//XStream xStream = new XStream();
		//默认的根节点是类名，这里把xml替换为类名
		xStream.alias("xml", respTextMessage.getClass());
		String xml = xStream.toXML(respTextMessage);
		System.out.println(xml);
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
