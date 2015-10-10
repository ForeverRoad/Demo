package junit;

import org.junit.Test;

import com.zcf.bean.Article;

public class TestUtil {
	
	@Test
	public void testClass(){
		Article article = new Article();
		showClass(article);
	}
	
	public void showClass(Object o){
		System.out.println(o.getClass().getSimpleName());
	}

}
