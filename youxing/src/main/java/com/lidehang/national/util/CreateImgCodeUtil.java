package com.lidehang.national.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.lidehang.dataInterface.model.constant.JsonArrayUtils;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

public class CreateImgCodeUtil {
	/**
	 * @param imgCode
	 * 通过base64加密的字符串解密生成图片保存在本地
	 */
	public static void createImgCode(String imgCode){
		JSONObject json = JsonArrayUtils.objectToJson(imgCode);
		BASE64Decoder decoder = new BASE64Decoder();
		String imgStr = json.getString("imgCode");
		File file = new File("D:\\LandCode\\imgCode.jpg");
		try {
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
			out.close();
	        System.out.println("验证码写到本地！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
