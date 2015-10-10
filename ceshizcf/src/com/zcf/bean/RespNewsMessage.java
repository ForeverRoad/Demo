package com.zcf.bean;

import java.util.List;

public class RespNewsMessage extends RespBaseMessage{
	private int ArticleCount;//图文消息个数
	private List<Article> Articles;//包含的图文信息内容
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	


	
}
