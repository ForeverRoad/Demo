package com.zcf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.util.SHA1;
import com.zcf.Services.BaseService;

public class WeChat extends HttpServlet {

	private static String Token = "ceshizcf";
	
	public WeChat() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
            //验证URL真实性  
            String signature = request.getParameter("signature");// 微信加密签名  
            String timestamp = request.getParameter("timestamp");// 时间戳  
            String nonce = request.getParameter("nonce");// 随机数  
            String echostr = request.getParameter("echostr");//随机字符串 
           
            List<String> params = new ArrayList<String>();  
            params.add(Token);  
            params.add(timestamp);  
            params.add(nonce);  
            //1. 将token、timestamp、nonce三个参数进行字典序排序  
            Collections.sort(params, new Comparator<String>() {  

                public int compare(String o1, String o2) {  
                    return o1.compareTo(o2);  
                }
            });  
            //2. 将三个参数字符串拼接成一个字符串进行sha1加密  
            String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));  
            if (temp.equals(signature)) {  
                response.getWriter().write(echostr);  
            }  
        
	}

	//处理微信服务器发来的消息
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//调用服务类进行业务处理,并返回字符串
		String respMessage;
		try {
			
			respMessage = BaseService.processRequest(request);
			// 响应消息 
	        PrintWriter out = response.getWriter();  
	        out.print(respMessage);  
	        out.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
