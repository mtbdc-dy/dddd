package com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import sun.misc.BASE64Decoder;
/**
 * 地税--纳税申申报表--通用申报表
 * @author Hobn
 *
 */
public class LandGrabSbbcxTy {
	public String selectLandTaxByDate(CloseableHttpClient httpclient,String userId){
		List<org.bson.Document> list = new ArrayList<>();
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
		//http://www.zjds-etax.cn/wsbs/api/sb/sbb?sbbz=Y&skssqq=2016-10-01&skssqz=2016-12-31&yzpzzlDm=BDA0610100
		//boolean flag=true;
		String response = TaxConstants.getMes(httpclient, "http://www.zjds-etax.cn/wsbs/api/sb/sbb?sbbz=Y&skssqq=2016-10-01&skssqz=2016-12-31&yzpzzlDm=BDA0610100",userId);
		/*if(response.equals("[]")){
			flag=false;
		}*/
		JSONArray array = JsonArrayUtils.objectToArrray(response);
		for(Object object:array){
			JSONObject json1 = JsonArrayUtils.objectToJson(object);
			Map<String, Object> baseMap=parseLssb(json1);
			//http://www.zjds-etax.cn/wsbs/api/sb/tysbb?pzxh=10023316000026920932
			response = TaxConstants.getMes(httpclient,"http://www.zjds-etax.cn/wsbs/api/sb/tysbb?pzxh="+json1.getString("pzxh"),userId);
			Map<String, Object> map1=parseLSSB(response);
			list.add(CompanyDataUtil.toDocument(baseMap,map1));
			new CompanyDataDaoImpl().addData("91330110583235134A", "11002", list);
		}
		return null;
	}

	private Map<String, Object> parseLSSB(String str) {
		JSONObject json=JsonArrayUtils.objectToJson(StringUtils.remZkh(str));
		List<Object> list=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		Map<String, Object> map1=new HashMap<>();
		Iterator<Object> it=json.keys();
		String index="11002005";
		String index1="11002005001";
 		while (it.hasNext()) {
			String key=it.next().toString();
			map.put(index1, json.getString(key));
			index1=String.valueOf((Long.parseLong(index1)+1));
		}
		if(map.size()>0){
			list.add(map);
		}
		map1.put(index, list);
		return map1;
	}

	private Map<String, Object> parseLssb(JSONObject json) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("11002001", json.getString("yzpzzl"));
		map.put("11002002", json.getString("sbrq"));
		map.put("11002003", json.getString("ybtse"));
		map.put("11002004", json.getString("kkrq"));
		map.put("serialNumber", "11002");
		return map;
	}
}
