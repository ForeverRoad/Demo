package com.zcf.Services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.util.MessageUtil;
import com.util.WXBizMsgCryptUtil;
import com.zcf.bean.RespNewsMessage;
import com.zcf.bean.RespTextMessage;

public class BaseService {
	static final String token = "ceshizcf";
	static final String encodingAesKey = "iSIjFvxcrRJxzFCKymBA7cuVbxP4c3pp986wgEbAjk9";
	static final String appId= "wx9c4a3bd673d28f06";
	
	public static String processRequest(HttpServletRequest request) throws DocumentException {
		//返回的xml格式信息
		String respMessage = null;
		//返回的xml格式进行加密处理
		String respMessageEncrypt = null;
		//使用安全模式获取的数据
    	String msgSignature = request.getParameter("msg_signature");
        String timeStamp = request.getParameter("timestamp");// 时间戳  
        String nonce = request.getParameter("nonce");// 随机数  
    	String encryptMsg = MessageUtil.readLine(request);//加密的xml内容
        
        //获取解密后的消息体内容
        String data = null;
        //获取加解密的解析类
		WXBizMsgCryptUtil pcUtil = new WXBizMsgCryptUtil();
		WXBizMsgCrypt pc = null;
        //进行解密处理
        try {
        	pc = pcUtil.getPc(token, encodingAesKey, appId);
			data = pc.decryptMsg(msgSignature, timeStamp, nonce, encryptMsg);
			
			
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//XML解析后的数据
		Map<String,String> requestMap = MessageUtil.parseXml(data);
		
		// 发送方帐号(open_id)
        String fromUserName = requestMap.get("FromUserName");  
        // 公众帐号  
        String toUserName = requestMap.get("ToUserName");  
        // 消息类型  
        String msgType = requestMap.get("MsgType");
        
        if ("text".equals(msgType)) {
        	TextService textService = new TextService();
        	//返回的xml类型字符串
    		respMessage = MessageUtil.toXml(textService.respTextMessage(requestMap));
    		System.out.println("test------"+respMessage);
		}else if("news".equals(msgType)){
			NewsService newsService = new NewsService();
			RespNewsMessage respNewsMessage = newsService.getNewsMessage(requestMap);
			//返回的xml类型字符串
    		respMessage = MessageUtil.toXml(respNewsMessage);
		}else if("event".equals(msgType)){
			String event = requestMap.get("Event");
			//订阅时触发事件
			if("subscribe".equals(event)){
				//返回提示
				TextService textService = new TextService();
				RespTextMessage respTextMessage = textService.wellcome(requestMap);
				respMessage = MessageUtil.toXml(respTextMessage);
			}else
			//取消订阅时触发事件	
			if("unsubscribe".equals(event)){
				//进行操作，比如记录一下，或者弹出页面，为啥取消
			}
		}
        //进行加密处理
        try {
        	respMessageEncrypt = pc.encryptMsg(respMessage, timeStamp, nonce);
        	System.out.println("加密过后的xml"+respMessageEncrypt);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respMessageEncrypt;
	}
	
}
