package com.lidehang.national.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.lidehang.dataInterface.model.constant.JsonArrayUtils;

public class TaxConstants {

	/**
	 * @param httpclient
	 * @param Url
	 * @return
	 * get请求
	 */
	public static String getMes(CloseableHttpClient httpclient,String Url){
	   String sp=null;
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(Url);  
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"); 
//            System.out.println("executing request " + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                // 打印响应状态    
//                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    // 打印响应内容长度    
//                    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
                    sp=EntityUtils.toString(entity, "UTF-8");
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
        	
        }
        return sp;
	}
	
	/**
	 * @param httpclient
	 * @param Url
	 * @param names
	 * @return
	 * post请求
	 */
	public static String postMes(CloseableHttpClient httpclient,String Url,List<BasicNameValuePair> names){
        HttpPost httppost = new HttpPost(Url); 
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"); 
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(BasicNameValuePair basicNameValuePair:names){
        	formparams.add(basicNameValuePair);
        }
        UrlEncodedFormEntity uefEntity; 
        String sp=null;
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
//            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    sp=EntityUtils.toString(entity, "UTF-8");
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return sp;
	}
	
	/**
	 * @param httpclient
	 * @param Url
	 * @param map
	 * @return  post请求方式使用json数据格式
	 */
	public static String postMes(CloseableHttpClient httpclient,String Url,Map<String,Object> map){
        HttpPost httppost = new HttpPost(Url); 
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"); 
        httppost.setHeader("Content-Type","application/json;charset=UTF-8");
        String json = JsonArrayUtils.objectToJson(map).toString();
        StringEntity stringEntity; 
        String sp=null;
        try {  
        	stringEntity = new StringEntity(json, "UTF-8");  
            httppost.setEntity(stringEntity);  
//            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    sp=EntityUtils.toString(entity, "UTF-8");
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return sp;
	}
	/**
	 * @param httpclient
	 * @param Url
	 * @param userId
	 * @return get请求加入指定header
	 */
	public static String getMes(CloseableHttpClient httpclient,String Url,String userId){
		String validIp="";
		RequestConfig requestConfig = null;
		if(validIp!=null){
			String[] ips = validIp.split("\\.");
			byte[] ip = new byte[4];
			for(int i = 0 ; i < 4; i ++){
				ip[i] = (byte)Integer.valueOf(ips[i]).intValue();
			}
			try {
				requestConfig = RequestConfig.custom().
						setExpectContinueEnabled(true).
						setConnectTimeout(1000).
						setSocketTimeout(1000).
						setConnectionRequestTimeout(1000).
						setCookieSpec(CookieSpecs.NETSCAPE).
						setCircularRedirectsAllowed(true)
						.setLocalAddress(InetAddress.getByAddress(ip)).
						build();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		   String sp=null;
	        try {  
	            // 创建httpget.    
	            HttpGet httpget = new HttpGet(Url);  
	            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"); 
	            httpget.setHeader("USERID",userId);
	            httpget.setConfig(requestConfig);
//	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.    
	            CloseableHttpResponse response = httpclient.execute(httpget);
	            try {  
	                // 获取响应实体    
	                HttpEntity entity = response.getEntity();  
	                // 打印响应状态    
//	                System.out.println(response.getStatusLine());  
	                if (entity != null) {  
	                    // 打印响应内容长度    
//	                    System.out.println("Response content length: " + entity.getContentLength());  
	                    // 打印响应内容    
	                    sp=EntityUtils.toString(entity, "UTF-8");
	                }  
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {
	        	
	        }
	        return sp;
		}
		return null;
		}
	
	
	/**
	 * @param httpclient
	 * @param Url
	 * @return get获取图片输入流
	 * get请求
	 */
	public static InputStream getImgCode(CloseableHttpClient httpclient,String Url){
		InputStream entity = null;
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(Url);
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"); 
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {  
                // 获取响应实体    
                entity = response.getEntity().getContent();  
            } finally {  
               // response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
        	
        }
		return entity;
	}
}
