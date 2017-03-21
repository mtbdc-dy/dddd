package com.lidehang.national.test;

import com.lidehang.national.localtax.fapiaocx.LandGrabKaijuFapiaocx;
import com.lidehang.national.localtax.fapiaocx.LandGrabShoudaoFapiaocx;
import com.lidehang.national.localtax.nashuishenbaocx.dianzijiaoshuifukuandayin.LandGrabDzjsfkdy;
import com.lidehang.national.localtax.nashuishenbaocx.koukuancx.LandGrabKkcx;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxFmsr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxFssr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxGyzcsysr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxQtsr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxShbxsr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxSssr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxXzsyxsr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxZfxjjsr;
import com.lidehang.national.localtax.nashuishenbaocx.qianshuicx.LandGrabQscxZxsr;
import com.lidehang.national.localtax.nashuishenbaocx.rukucx.LandGrabRukucx;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx.LandGrab;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx.LandGrabSbbcxCjrb;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx.LandGrabSbbcxFjs;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx.LandGrabSbbcxTy;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaobiaocx.LandGrabSbbcxYhs;
import com.lidehang.national.localtax.nashuishenbaocx.shenbaocx.LandGrabSbcx;
import com.lidehang.national.localtax.shuiwudengjicx.LandGrabJbxxcx;
import com.lidehang.national.localtax.shuiyuanbaobiaocx.LandGrabSyfdcqyxxb;
import com.lidehang.national.localtax.shuiyuanbaobiaocx.LandGrabSyqycwxxb;
import com.lidehang.national.localtax.shuiyuanbaobiaocx.LandGrabSyqydcb;
import com.lidehang.national.localtax.shuiyuanbaobiaocx.LandGrabSyqyssxx;
import com.lidehang.national.localtax.weizhangcx.LandGrabWeizhangWeiguicx;
import com.lidehang.national.localtax.wenshucx.LandGrabWenshucx;
import com.lidehang.national.localtax.xinyongcx.LandGrabXydjcx;

public class TestLocaltax {
	public static void main(String[] args) {
//		 new LandGrab().selectLandTaxByDate(); //纳税申报 申报表查询 社会保险费缴费申报表（适用单位缴费人）   多层json
	
//		 new LandGrabSbbcxTy().selectLandTaxByDate(); //纳税申报 申报表查询 通用申报表                           11002
//		 new LandGrabSbbcxFjs().selectLandTaxByDate(); //纳税申报 申报表查询 教育费附加申报表		 11003
//		 new LandGrabSbbcxYhs().selectLandTaxByDate(); //纳税申报 申报表查询  印花税纳税申报（报告）表      多层json
//		 new LandGrabSbbcxCjrb().selectLandTaxByDate(); //纳税申报 申报表查询 残疾人就业保障金缴费申报表    11004
		
//		 new LandGrabXydjcx().selectLandTaxByDate(); //纳税申报 信用等级查询      				 11001
		 
		// new LandGrabKkcx().selectLandTaxByDate(); //纳税申报 扣款查询
		// new LandGrabQscxSssr().selectLandTaxByFeestaxes(); //纳税申报 欠税查询 税收收入
//		new LandGrabQscxShbxsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 社会保险基金收入
//		new LandGrabQscxFssr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 非税收入
//		new LandGrabQscxZfxjjsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 政府性基金收入
//		new LandGrabQscxZxsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 专项收入
//		new LandGrabQscxXzsyxsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 行政事业性收费收入
//		new LandGrabQscxFmsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 罚没收入
//		new LandGrabQscxGyzcsysr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 国有资源（资产）有偿使用收入
//		new LandGrabQscxQtsr().selectLandTaxByFeestaxes(); // 纳税申报 欠税查询 其他收入
//	    new LandGrabDzjsfkdy().selectLandTaxByDate(); // 纳税申报 电子缴税付款凭证打印
		
		
//		new LandGrabSbcx().selectLandTaxByDate();//纳税申报  申报查询    多项选择
//		new LandGrabRukucx().selectLandTaxByDate();//纳税申报  入库查询    多项选择
		 
//		 new LandGrabShoudaoFapiaocx().selectLandTaxByDate(); //发票查询 收到的电子发票查询
		// new LandGrabKaijuFapiaocx().selectLandTaxByDate(); //发票查询 开具的电子发票查询
		// new LandGrabWenshucx().selectLandTaxByDate(); //文书查询
		// new LandGrabWeizhangWeiguicx().selectLandTaxByDate(); //违章违规综合查询
		// new LandGrabSyqyssxx().selectLandTaxByDate(); //税源报表查询 重点税源企业税收信息
		// new LandGrabSyqydcb().selectLandTaxByDate(); //税源报表查询
		// 重点税源企业景气调查问卷（月报）表
		// new LandGrabSyqycwxxb().selectLandTaxByDate(); //税源报表查询
		// 重点税源企业财务信息（季报）表
		// new LandGrabSyfdcqyxxb().selectLandTaxByDate("201601", "201702");//税源报表查询 重点税源房地产企业开发经营信息（季报）表
		// new LandGrabJbxxcx().selectLandTaxByDate(); //税务登记查询 基本信息查询

	}
}
