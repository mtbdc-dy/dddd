package com.lidehang.data.collection.service.gs.site;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.lidehang.data.collection.exception.SiteLoginFailedException;
import com.lidehang.data.collection.exception.SiteNotLoginException;
import com.lidehang.data.collection.model.param.GSSiteParams;
import com.lidehang.data.collection.service.SiteHandler;
import com.lidehang.data.collection.service.gs.GSModuleBase;
import com.lidehang.national.util.StringUtils;
import com.lidehang.national.util.VPNUtils;

/**
 * 浙江国税网站
 */
public class GSSiteHandler extends SiteHandler<GSSiteParams,GSModuleBase<GSSiteHandler>> {


	@Override
	public void begin() {
		VPNUtils.ConnectVPN(params.getVpnUser(), params.getVpnPwd());
	}

	@Override
	public void login() throws SiteLoginFailedException {
		if(client == null){
			client = HttpClients.createDefault();
			List<BasicNameValuePair> values = new ArrayList<BasicNameValuePair>();

			values.add(new BasicNameValuePair("kaptchafield", ""));
			values.add(new BasicNameValuePair("number", params.getNationalTaxUser()));
			values.add(new BasicNameValuePair("password", params.getNationalTaxPwd()));
			
			postPage("http://100.0.0.120:6000/dzswj/login.jsp?rand=0.6784915976252301",values);
			String html = getPage("http://100.0.0.120:6000/dzswj/information.jsp");
			getPage("http://100.0.0.120:6000/dzswj/getpages.jsp?node=sbns&djxh="+StringUtils.getDjxh(html));
			
//			String indexMes = getPage("http://100.0.0.1:8001/ctais2/wssb/sb_nssb.jsp");
			System.out.println(StringUtils.getDjxh(html));
		}
	}

	@Override
	public void exceptionFilter(String html) throws SiteNotLoginException {
		// TODO Auto-generated method stub
	}

	@Override
	public void end() {
		VPNUtils.DisconnectVPN(params.getVpnUser());
	}

}
