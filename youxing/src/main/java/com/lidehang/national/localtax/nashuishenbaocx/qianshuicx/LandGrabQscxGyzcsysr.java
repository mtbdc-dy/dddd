package com.lidehang.national.localtax.nashuishenbaocx.qianshuicx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.lidehang.dataInterface.model.constant.JsonArrayUtils;
import com.lidehang.national.util.CreateImgCodeUtil;
import com.lidehang.national.util.MD5Util;
import com.lidehang.national.util.TaxConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import sun.misc.BASE64Decoder;

/**
 * 地税--纳税申申报表--欠税查询--国有资源（资产）有偿使用收入
 * 
 * @author Hobn
 *
 */
public class LandGrabQscxGyzcsysr {
	public String selectLandTaxByFeestaxes(CloseableHttpClient httpclient,String userId) {
		/*CloseableHttpClient httpclient = HttpClients.createDefault();
		String imgCode = TaxConstants.getMes(httpclient,
				"http://www.zjds-etax.cn/wsbs/api/home/auth/imgcode?sid=" + Math.random());
		CreateImgCodeUtil.createImgCode(imgCode);
		Scanner in = new Scanner(System.in);
		String code = in.next();
		System.out.println(code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgCode", code);
		map.put("mmqrdbj", "0");
		map.put("mobile", "3892");
		map.put("password", MD5Util.MD5("changtai1836"));
		map.put("username", "杭州烁云科技有限公司");
		String response = TaxConstants.postMes(httpclient, "http://www.zjds-etax.cn/wsbs/api/home/auth/login", map);
		JSONObject json = JsonArrayUtils.objectToJson(response);
		String userId = json.getString("USERID");*/
		// http://www.zjds-etax.cn/wsbs/api/sscx/nssb/qs?zsxmDm=30700
		String response = TaxConstants.getMes(httpclient, "http://www.zjds-etax.cn/wsbs/api/sscx/nssb/qs?zsxmDm=30700",
				userId);
		System.out.println(response);
		return null;
	}
}
