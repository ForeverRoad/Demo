package junit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class TestHttp {
	static String params = "";
	static String accessToken = "gLbmuMj7HetQu-gR_Nv0BcuMShCmUjsFKw9vQ6AfLFp83EqaW0tAUuvEi5euXcAzNWlraXJBK5nmF9vP6SqftQHxJTQdR-VVPdVRfwTyOKc";
	@Test
	public  void createMenu() {
        StringBuffer bufferRes = new StringBuffer();
        try {
                URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+ accessToken);
                HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
                // 连接超时
                conn.setConnectTimeout(25000);
                // 读取超时 --服务器响应比较慢,增大时间
                conn.setReadTimeout(25000);
                HttpURLConnection.setFollowRedirects(true);
                // 请求方式
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
                conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
                conn.connect();
                // 获取URLConnection对象对应的输出流
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                // 发送请求参数
                //out.write(URLEncoder.encode(params,"UTF-8"));
                out.write(params);
                out.flush();
                out.close();
                InputStream in = conn.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String valueString = null;
                while ((valueString=read.readLine())!=null){
                        bufferRes.append(valueString);
                }
                System.out.println(bufferRes.toString());
                in.close();
                if (conn != null) {
                        // 关闭连接
                        conn.disconnect();
                }

        } catch (Exception e) {

                e.printStackTrace();
        }
	}
	
	
	public String getJson(){
		String result ="{\"button\":[{	\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		return result;
	}
	
	
	@Test
	public void testCreateTicket(){
		StringBuffer bufferRes = new StringBuffer();
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": zcf}}}";
        try {
                URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ accessToken);
                HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
                // 连接超时
                conn.setConnectTimeout(25000);
                // 读取超时 --服务器响应比较慢,增大时间
                conn.setReadTimeout(25000);
                HttpURLConnection.setFollowRedirects(true);
                // 请求方式
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
                conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
                conn.connect();
                // 获取URLConnection对象对应的输出流
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                // 发送请求参数
                //out.write(URLEncoder.encode(params,"UTF-8"));
                out.write(json);
                out.flush();
                out.close();
                InputStream in = conn.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String valueString = null;
                while ((valueString=read.readLine())!=null){
                        bufferRes.append(valueString);
                }
                System.out.println(bufferRes.toString());
                in.close();
                if (conn != null) {
                        // 关闭连接
                        conn.disconnect();
                }

        } catch (Exception e) {

                e.printStackTrace();
        }
	}
}
