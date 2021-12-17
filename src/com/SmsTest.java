package com;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *
 * @author: shuangfeng_li 2021/8/24 11:05
 */
public class SmsTest {
    private static final String U1 = "http://120.77.14.55:8888/v2sms.aspx";

    public static void main(String[] args) {

//        List<String> mobiles = new ArrayList<>();
//        mobiles.add("18320953439");
//
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < mobiles.size(); i++) {
//            builder.append(mobiles.get(i));
//            if (i != (mobiles.size() - 1)) {
//                builder.append(",");
//            }
//        }
//        Map map = new HashMap();
//        map.put("mobile", builder.toString());
//        map.put("content", "【NOKNOK】亲爱的召唤师，kk你来参加擂台赛啦！报名即可参与5V5擂台赛，每场获胜即送20现金，连胜最高可得80现金。点击 https://xuz0.cn/2TRlv2uc 领现金");
//        map.put("action", "send");
//        Map retMap = commonPost(U1, map);
//        System.out.println(retMap);
        sendOut();
    }

    public static void sendOut() {
        List<String> mobiles = new ArrayList<>();
        mobiles.add("18320953439");
        String content = "【提醒我吧】日程提醒，提醒事项：（打疫苗），提醒时间：2021-08-20 10:59，点击 gougoumoney.com/jp 进入小程序";

        String smsTagName = null;
        String smsText = null;
        int idx;
        if (content.startsWith("【")) {
            idx = content.indexOf("】");
            smsTagName = content.substring(1, idx);
            smsText = content.substring(idx + 1);
        } else if (content.startsWith("[")) {
            idx = content.indexOf("]");
            smsTagName = content.substring(1, idx);
            smsText = content.substring(idx + 1);
        }

        JSONArray mobileArr = new JSONArray();
        Iterator var7 = mobiles.iterator();

        while (var7.hasNext()) {
            String mobile = (String) var7.next();
            mobileArr.add(mobile);
        }

        JSONArray contentArr = new JSONArray();
        contentArr.add(smsText);
        Map map = new HashMap();
        map.put("phoneNum", mobileArr);
        map.put("content", contentArr);
        map.put("smsType", "false");
        map.put("signature", "【".concat(smsTagName).concat("】"));
        Map retMap = commonPost("http://www.qd6666.cn/qiandi-menber/V3.0.0/api/sendMsg.do", map);
        System.out.println(retMap);

    }

    public final String SEND_URL = "http://www.qd6666.cn/qiandi-menber/V3.0.0/api/sendMsg.do";
    public final String STATE_URL = "http://www.qd6666.cn/qiandi-menber/V3.0.0/api/getUserRecived.do";
    public final String REPLY_URL = "http://www.qd6666.cn/qiandi-menber/V3.0.0/api/reply.do";
    private static String accountName = "18565758195";
    private static String password = "2019xmg";
    private  static JSONObject commonPost(String url, Map pam) {
        Map map = new HashMap();
        map.put("accountName", accountName);
        map.put("password", password);
        map.putAll(pam);

        try {
            String ret = HttpUtils.doPost(url, map);
            JSONObject jsonObject = JSONObject.parseObject(ret);
            jsonObject.put("ORIGINAL_CONTENT", ret);
            return jsonObject;
        } catch (Exception var6) {
            return null;
        }
    }

//    private static Map commonPost(String url, Map pam) {
//        String id = "853";
//        String loginId = "szsxlq";
//        String pwd = "hudong123A";
//
//        Map map = new HashMap();
//        long time = System.currentTimeMillis();
//        String sign = Utils.EncoderByMd5(loginId + pwd + time);
//        map.put("userid", id);
//        map.put("timestamp", time);
//        map.put("sign", sign);
//        map.putAll(pam);
//        try {
//            String ret = HttpUtils.doPost(url, map);
//            if (ret != null) {
//                int idx = ret.indexOf("?>");
//                ret = ret.substring(idx + 2, ret.length());
//            }
//            Map retMap = SmsXmlUtils.xmltoMap(ret);
////            if (url.equals(U1)) {
//            retMap.put("ORIGINAL_CONTENT", ret);
////            }
//            return retMap;
//        } catch (Exception e) {
//            return null;
//        }
//    }

}
