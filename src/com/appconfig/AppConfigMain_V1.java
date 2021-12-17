package com.appconfig;

import com.bill.BillMain;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2019/10/10 15:26
 */
public class AppConfigMain_V1 {

    public static void main(String[] args) {
//        appQuery("310000015000288728", "310000016000290258", "金蝶汽车网络科技有限公司");
//        bindPublicAccount();//公众号




    }

    public static void appQuery(String agentId, String memberId, String merchName) {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("agentId", agentId);
        jsonData.addProperty("memberId", memberId);
        jsonData.addProperty("merchName", merchName);

        String checkValue = encode(jsonData.toString(), "chinapnr", "UTF-8");

        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = BillMain.sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB1418/queryWxConfig", postMap);
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void bindPublicAccount() {
        String agentId = "310000015000288728"; // 代理商号
        String memberId = "310000016000290258";//商户号
        String merchName = "金蝶汽车网络科技有限公司";//商户名
        String wechatPubNumAppid = "wx7ec22ce90472d754";//APPID
        String wechatPubNumAuth = "https://cargeer.com/auto/weixin/qchulian02/business/wsc/";//公众号支付授权目录

        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("reqSerialNum", getNumber(120));
        //代理商号
        jsonData.addProperty("agentId", agentId);
        //商户号
        jsonData.addProperty("memberId", memberId);
        //商户名
        jsonData.addProperty("merchName", merchName);
        // APPID
        jsonData.addProperty("wechatPubNumAppid", wechatPubNumAppid);
        //公众号支付授权目录
        jsonData.addProperty("wechatPubNumAuth", wechatPubNumAuth);

        String checkValue = encode(jsonData.toString(), "chinapnr", "UTF-8");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = BillMain.sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB1418/wxConfig", postMap);
            System.out.println(ret);
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
