package com.zcf.Services;

import java.util.Map;

import com.zcf.bean.RespTextMessage;
/**
 * 
 * ClassName: TextService 
 * @Description: 文本类型的处理，并返回respTextMessage类型对象的类
 * @author zcf
 * @date 2015-10-8
 */
public class TextService{
	//关注时提示信息
	public RespTextMessage wellcome(Map<String,String> requestMap){
		//new一个文本类型的对象
        RespTextMessage respTextMessage = new RespTextMessage();
    	//设置返回文本的xml格式
    	respTextMessage.setFromUserName(requestMap.get("ToUserName"));
    	respTextMessage.setToUserName(requestMap.get("FromUserName"));
    	respTextMessage.setCreateTime(System.currentTimeMillis());
    	respTextMessage.setMsgType("text");
    	StringBuffer buffer = appendBuffer().insert(0,"感谢关注,让你的生活更精彩\n");
    	respTextMessage.setContent(buffer.toString());
		return respTextMessage;
	}
	//获取提示，提示操作
	public RespTextMessage getTips(Map<String,String> requestMap){
		//new一个文本类型的对象
        RespTextMessage respTextMessage = new RespTextMessage();
    	//设置返回文本的xml格式
    	respTextMessage.setFromUserName(requestMap.get("ToUserName"));
    	respTextMessage.setToUserName(requestMap.get("FromUserName"));
    	respTextMessage.setCreateTime(System.currentTimeMillis());
    	respTextMessage.setMsgType("text");
    	//获取帮助信息的buffer
    	StringBuffer buffer = appendBuffer();
    	
    	respTextMessage.setContent(buffer.toString());
		return respTextMessage;
	}
	//基本回复操作
	public Object respTextMessage(Map<String,String> requestMap){
		
		Object object = null;
		//new一个文本类型的对象
        RespTextMessage respTextMessage = new RespTextMessage();
    	//设置返回文本的xml格式
    	respTextMessage.setFromUserName(requestMap.get("ToUserName"));
    	respTextMessage.setToUserName(requestMap.get("FromUserName"));
    	respTextMessage.setCreateTime(System.currentTimeMillis());
    	respTextMessage.setMsgType("text");
    	if("1".equals(requestMap.get("Content"))){
    		respTextMessage.setContent("自己看天！");
    	}else if("2".equals(requestMap.get("Content"))){
    		respTextMessage.setContent("穷逼，不会坐地铁啊！");
    	}else if("3".equals(requestMap.get("Content"))){
    		respTextMessage.setContent("别周边了，屌丝是约不上炮的！");
    	}else if("4".equals(requestMap.get("Content"))){
    		respTextMessage.setContent("哥唱歌要命，你不想活 了吗？");
    	}else if("5".equals(requestMap.get("Content"))){
    		NewsService newsService = new NewsService();
    		//调用消息的处理方法
    		object = newsService.getNewsMessage(requestMap);
    	}else if("?".equals(requestMap.get("Content"))||
    			"help".equals(requestMap.get("Content"))||
    			"？".equals(requestMap.get("Content"))){
    		//返回获取帮助请求的内容
    		return getTips(requestMap);
    		//respTextMessage = getTips(requestMap);
    	}else {
    		respTextMessage.setContent("未识别关键字，请确认后重新输入！");
    	}
    	if(object==null){
    		object = respTextMessage;
    	}
		return object;
	}
	
	
	public StringBuffer appendBuffer(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好,我是小q,请回复数字选择服务:").append("\n\n");
    	buffer.append("1 天气预报").append("\n");
    	buffer.append("2 公交查询").append("\n");
    	buffer.append("3 周边搜索").append("\n");
    	buffer.append("4 歌曲点播").append("\n");
    	buffer.append("回复'?'显示此帮助菜单");
    	return buffer;
		
	}
}
