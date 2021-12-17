package com.appconfig;

import com.bill.BillMain;
import com.bill.huifu.cfca.SignUtil;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2019/10/10 15:26
 */
public class AppConfigMain_V2 {

    public static void main(String[] args) {
        //        bindMiniPro_v2();
//        bindPublicAccount_v2();
        appQuery_v2("310000016002278478");  //�̻���
    }



    public static void appQuery_v2(String memberId) {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.2");
        // �̻���
        jsonData.addProperty("memberId", memberId);

        // CFCA ǩ��
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = BillMain.sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB7019/v2", postMap);
            String decodeRet = BillMain.checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindMiniPro_v2() {
        String reqSerialNum = getNumber(120);
        System.out.println(reqSerialNum);

        String wechatPubNumAppid = "";//APPID
        String agentId = ""; // �����̺� 310000015000288728
        String memberId = "";//�̻��� 310000016002098216

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.1");
        // �̻���
        jsonData.addProperty("reqSerialNum", reqSerialNum);
        //�����̺�
        jsonData.addProperty("agentId", agentId);
        //�̻���
        jsonData.addProperty("memberId", memberId);
        // ���ں�֧��Appid С����APPID
        jsonData.addProperty("wechatPubNumAppid", wechatPubNumAppid);
        // 01���߿�-���ں����á�02-С��������
        jsonData.addProperty("configType", "02");
        //֧������  01 ��ͨ����;02 �ǻ۲���;03 У԰�����;04 ˽��У԰�����;05 ����
        jsonData.addProperty("payScene", "01");

        // CFCA ǩ��
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = BillMain.sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB1418/wxConfig/v2", postMap);
            String decodeRet = BillMain.checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindPublicAccount_v2() {
        // http://api.cloudpnr.com/nspos/taipaizhifu.html#v2
        String reqSerialNum = getNumber(120);
        String agentId = "310000015002093231"; // 代理商号
        String memberId = "310000016002099398";//商户号
        String wechatPubNumAppid = "wxc0c1b488114f4cba";//APPID
        String wechatPubNumAuth = "http://cargeer.com/auto/weixin/hongtongqiche/business/wsc/";//公众号支付授权目录

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.1");
        // 请求流水号
        jsonData.addProperty("reqSerialNum", reqSerialNum);
        //代理商号
        jsonData.addProperty("agentId", agentId);
        //商户号
        jsonData.addProperty("memberId", memberId);
        // APPID
        jsonData.addProperty("wechatPubNumAppid", wechatPubNumAppid);
        //公众号支付授权目录
        jsonData.addProperty("wechatPubNumAuth", wechatPubNumAuth);
        // 01或者空-公众号配置
        // 02-小程序配置
        jsonData.addProperty("configType", "01");
        //支付场景  01 普通线下
        //02 智慧餐饮
        //03 校园零费率
        //04 私立校园零费率
        //05 线上
        //06教培
        //07教育K12
        jsonData.addProperty("payScene", "01");

        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = BillMain.sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB1418/wxConfig/v2", postMap);
            String decodeRet = BillMain.checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNumber(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(new Random().nextInt(9));
        }
        return str.toString();
    }


    public static String encode(String content, String salt, String charset) {
        StringBuilder newContent = new StringBuilder();
        newContent.append(content).append(salt);
        MessageDigest md = null;
        String strDes = null;

        try {
            Object e = null;

            byte[] e1;
            try {
                e1 = newContent.toString().getBytes(charset);
            } catch (UnsupportedEncodingException var8) {
                throw new RuntimeException(var8);
            }

            md = MessageDigest.getInstance("SHA-256");
            md.update(e1);
            strDes = bytes2Hex(md.digest());
            return strDes;
        } catch (NoSuchAlgorithmException var9) {
            return null;
        }
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;

        for (int i = 0; i < bts.length; ++i) {
            tmp = Integer.toHexString(bts[i] & 255);
            if (tmp.length() == 1) {
                des = des + "0";
            }

            des = des + tmp;
        }

        return des;
    }

}
