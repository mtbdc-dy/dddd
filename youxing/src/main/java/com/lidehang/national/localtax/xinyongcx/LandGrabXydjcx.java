package com.lidehang.national.localtax.xinyongcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bson.Document;

import com.lidehang.data.collection.dao.impl.CompanyDataDaoImpl;
import com.lidehang.data.collection.util.CompanyDataUtil;
import com.lidehang.dataInterface.model.constant.JsonArrayUtils;
import com.lidehang.national.util.CreateImgCodeUtil;
import com.lidehang.national.util.MD5Util;
import com.lidehang.national.util.StringUtils;
import com.lidehang.national.util.TaxConstants;

import net.sf.json.JSONObject;
/**
 * 地税--纳税申申报表--印花税纳税申报（报告）表
 * @author Hobn
 *
 */
public class LandGrabXydjcx {
	public String selectLandTaxByDate(CloseableHttpClient httpclient,String userId){
		/*CloseableHttpClient httpclient = HttpClients.createDefault();
		String imgCode = TaxConstants.getMes(httpclient, "http://www.zjds-etax.cn/wsbs/api/home/auth/imgcode?sid="+Math.random());
		CreateImgCodeUtil.createImgCode(imgCode);
		Scanner in = new Scanner(System.in);
		String code = in.next();
		System.out.println(code);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("imgCode", code);
		map.put("mmqrdbj", "0");
		map.put("mobile", "3892");
		map.put("password", MD5Util.MD5("changtai1836"));
		map.put("username", "杭州烁云科技有限公司");
		String response = TaxConstants.postMes(httpclient,"http://www.zjds-etax.cn/wsbs/api/home/auth/login",map);
		JSONObject json = JsonArrayUtils.objectToJson(response);
		String userId = json.getString("USERID");*/
		//http://www.zjds-etax.cn/wsbs/api/sscx/xydj?pdnd=2016
		String response = TaxConstants.getMes(httpclient, "http://www.zjds-etax.cn/wsbs/api/sscx/xydj?pdnd=2016",userId);
		JSONObject json1=JsonArrayUtils.objectToJson(StringUtils.remZkh(response));
		Map<String, Object> map1=new HashMap<>();
		map1.put("serialNumber", "11001");
		Iterator it = json1.keys();  
		String index="110001001";
		while(it.hasNext()){
			String key=it.next().toString();
			map1.put(index, json1.getString(key));
			index = String.valueOf((Long.parseLong(index) + 1));
		}
		List<Document> list=new ArrayList<>();
		list.add(CompanyDataUtil.toDocument(map1));
		new CompanyDataDaoImpl().addData("91330110583235134A", "11001", list);
		//System.out.println(response);
		return null;
	}
}
