package com.lidehang.national.foreigncurrency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lidehang.national.util.MD5Util;
import com.lidehang.national.util.StringUtils;
import com.lidehang.national.util.TaxConstants;

public class ForeignCurrencyGrab {

	public String selectForeignCurrencyByDate() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		InputStream imgCode = TaxConstants.getImgCode(httpclient,
				"http://asone.safesvc.gov.cn/asone/jsp/code.jsp?refresh="+Math.random());
		createImgCode(imgCode);
		Scanner in = new Scanner(System.in);
		String code = in.next();
		List<BasicNameValuePair> values = new ArrayList<BasicNameValuePair>();
		values.add(new BasicNameValuePair("orgCode", "716103128"));
		values.add(new BasicNameValuePair("pwd", MD5Util.MD5("88065212Zl")));
		values.add(new BasicNameValuePair("check", code));
		values.add(new BasicNameValuePair("userCode", "001"));
		String response = TaxConstants.postMes(httpclient,"http://asone.safesvc.gov.cn/asone/jsp/checkCode.jsp",values);
		System.out.println(response);
		values.clear();
		Document document = Jsoup.parse(StringUtils.rpAll(response));
		String safeValidateCode = document.getElementsByAttributeValue("name", "safeValidateCode").val();
		String backUrl = document.getElementsByAttributeValue("name", "backUrl").val();
		String enterUrl = document.getElementsByAttributeValue("name", "enterUrl").val();
		String userCode = document.getElementsByAttributeValue("name", "userCode").val();
		String pwd = document.getElementsByAttributeValue("name", "pwd").val();
		String orgCode = document.getElementsByAttributeValue("name", "orgCode").val();
		values.add(new BasicNameValuePair("safeValidateCode", safeValidateCode));
		values.add(new BasicNameValuePair("backUrl", backUrl));
		values.add(new BasicNameValuePair("enterUrl", enterUrl));
		values.add(new BasicNameValuePair("userCode", userCode));
		values.add(new BasicNameValuePair("pwd", pwd));
		values.add(new BasicNameValuePair("orgCode", orgCode));
		response = TaxConstants.postMes(httpclient,"http://asone.safesvc.gov.cn/asone/servlet/AuthorityServlet",values);
		System.out.println(response);
		return null;
	}

	/**
	 * @param ins
	 * 通过输入流在本地生成验证码图片
	 */
	public static void createImgCode(InputStream ins) {
		File file = new File("D:\\ForCurCode\\imgCode.jpg");
        try {
	        FileOutputStream fileout = new FileOutputStream(file);  
	        /** 
	         * 根据实际运行效果 设置缓冲区大小 
	         */  
	        byte[] buffer=new byte[1024];  
	        int ch = 0;  
			while ((ch = ins.read(buffer)) != -1) {  
			    fileout.write(buffer,0,ch);  
			}
			ins.close();  
	        fileout.flush();  
	        fileout.close();  
	        System.out.println("验证码写到本地！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
	 }

	public static void main(String[] args) {
		new ForeignCurrencyGrab().selectForeignCurrencyByDate();
	}
	
	public InputStream getImgCode(CloseableHttpClient httpclient){
		InputStream imgCode = TaxConstants.getImgCode(httpclient,
				"http://asone.safesvc.gov.cn/asone/jsp/code.jsp?refresh="+Math.random());
		return imgCode;
	}
}
