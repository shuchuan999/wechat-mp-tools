package wechat.core.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequestUtils {
    //发送http请求并返回请求结果(post,同步)
    public static String sendRequest(String url,String jsonString){
        HttpEntity responseEntity = null;
        String returnStr="";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();

            returnStr= EntityUtils.toString(responseEntity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnStr;
    }
    //发送http请求并返回请求结果(get,同步)
    public static String sendGetRequest(String url){
        String content="";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个 GET 请求
        HttpGet httpGet = new HttpGet(url);
        try {
            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //取响应的结果
            int statusCode = response.getStatusLine().getStatusCode();
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
            //关闭httpclient
            response.close();
            httpClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }

    public static <T> T getEntityFromGet(Class<T> tClass,String url){
        String json=sendGetRequest(url);
        Gson gson=new Gson();
        return gson.fromJson(json,tClass);
    }

}
