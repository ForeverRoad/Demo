package com.zcf.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zcf.bean.Article;
import com.zcf.bean.RespNewsMessage;

public class NewsService {

	public RespNewsMessage getNewsMessage(Map<String,String> requestMap) {
		RespNewsMessage respNewsMessage = new RespNewsMessage();
		respNewsMessage.setToUserName(requestMap.get("FromUserName"));
		respNewsMessage.setFromUserName(requestMap.get("ToUserName"));
		respNewsMessage.setCreateTime(System.currentTimeMillis());
		respNewsMessage.setMsgType("news");
		respNewsMessage.setArticleCount(3);
		
		List<Article> list = new ArrayList<Article>();
		Article article = null;
		for(int i=0;i<3;i++){
			article = new Article();
			article.setDescription((i+1)+"号展示");
			article.setTitle((i+1)+"号标题");
			article.setPicUrl("http://img.zcool.cn/community/019e0155b092986ac725ca50cc45c9.jpg");
			article.setUrl("http://ceshizcf1.sinaapp.com");
			list.add(article);
		}
		respNewsMessage.setArticles(list);
		
		return respNewsMessage;
	}

}
