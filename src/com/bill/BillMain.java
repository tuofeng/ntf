package com.bill;

import com.bill.huifu.cfca.SignUtil;
import com.bill.huifu.cfca.VerifyResult;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.math.BigDecimal;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/4/25 20:18
 */
@SuppressWarnings("all")
public class BillMain {

    public static void main(String[] args) throws Exception {

//        unifiedOrder();
//
//        downLoadBill();
//        decodeBill("D:\\交易对账单示例0423 - 副本.csv", LogFactory.getLog(BillMain.class));
//
//        unifiedQrOrder();
//
//        checkDataFromHfReq("{\"respDesc\":\"??\",\"jsonData\":\"{\\\"transTime\\\":\\\"191202\\\",\\\"tokenId\\\":\\\"wx0919120260332121ec800c890280045367\\\",\\\"orderId\\\":\\\"20190509E10295688029\\\",\\\"merName\\\":\\\"??????\\\",\\\"ordId\\\":\\\"20190509E10295688029\\\",\\\"payChannelType\\\":\\\"W\\\",\\\"productSeqTime\\\":\\\"191202\\\",\\\"outOrdId\\\":\\\"47DD1795F14C7F2D\\\",\\\"subOpenId\\\":\\\"o_eCFxMoX8qm8ZZYhMDVn0mTIp54\\\",\\\"transDate\\\":\\\"20190509\\\",\\\"productSeqDate\\\":\\\"20190509\\\",\\\"payInfo\\\":\\\"{\\\\\\\"appId\\\\\\\":\\\\\\\"wx7ec22ce90472d754\\\\\\\",\\\\\\\"timeStamp\\\\\\\":\\\\\\\"1557400322\\\\\\\",\\\\\\\"nonceStr\\\\\\\":\\\\\\\"4bd1e9397a8444e5bdcbc6dac0cbb6c3\\\\\\\",\\\\\\\"package\\\\\\\":\\\\\\\"prepay_id=wx0919120260332121ec800c890280045367\\\\\\\",\\\\\\\"signType\\\\\\\":\\\\\\\"RSA\\\\\\\",\\\\\\\"paySign\\\\\\\":\\\\\\\"GGdFB6pIPWTeRRxebCU5tdVPKhp9BMpNVr7unCdN/Mcv16r3bWUHD5O/1DO7KNat9HXNL0MjLIj9YdTj8U37F2F1vjpgIvsp+2D8+lyfrsK7293mXJ9BbPf8n2rRCtcxTuc5bzkpytbvNxco6SvFFcJJcBw9LiBEWiKn6xj+O/0fASvGVztI5bF47w7WEk3Aqls3KZxGHY46u7Y7RyMXbtgCPFS/Ptx+SE32Nb0BCczQt58vzSpZV5te3soF9lf2S0aK1OvVExzUFIF37IXDI6umC9il6gAMzV5XzMA3zMTJkYab7G/YwYqiSWibh1l2d299F5l/UfQMzbVWqF4q6g==\\\\\\\"}\\\",\\\"orderDate\\\":\\\"20190509\\\",\\\"termOrdId\\\":\\\"ba2162c1-e0cf-4210-b447-d65485780c0d\\\",\\\"memberId\\\":\\\"310000016000290258\\\"}\",\"checkValue\":\"TUlJS29nWUpLb1pJaHZjTkFRY0NvSUlLa3pDQ0NvOENBUUV4RHpBTkJnbGdoa2dCWlFNRUFnRUZBRENDQkFrR0NTcUdTSWIzRFFFSEFhQ0NBL29FZ2dQMmV5SjBjbUZ1YzFScGJXVWlPaUl4T1RFeU1ESWlMQ0owYjJ0bGJrbGtJam9pZDNnd09URTVNVEl3TWpZd016TXlNVEl4WldNNE1EQmpPRGt3TWpnd01EUTFNelkzSWl3aWIzSmtaWEpKWkNJNklqSXdNVGt3TlRBNVJURXdNamsxTmpnNE1ESTVJaXdpYldWeVRtRnRaU0k2SXVtSGtlaWR0dWF4dmVpOXB1YTFpK2l2bFNJc0ltOXlaRWxrSWpvaU1qQXhPVEExTURsRk1UQXlPVFUyT0Rnd01qa2lMQ0p3WVhsRGFHRnVibVZzVkhsd1pTSTZJbGNpTENKd2NtOWtkV04wVTJWeFZHbHRaU0k2SWpFNU1USXdNaUlzSW05MWRFOXlaRWxrSWpvaU5EZEVSREUzT1RWR01UUkROMFl5UkNJc0luTjFZazl3Wlc1SlpDSTZJbTlmWlVOR2VFMXZXRGh4YlRoYVdsbG9UVVJXYmpCdFZFbHdOVFFpTENKMGNtRnVjMFJoZEdVaU9pSXlNREU1TURVd09TSXNJbkJ5YjJSMVkzUlRaWEZFWVhSbElqb2lNakF4T1RBMU1Ea2lMQ0p3WVhsSmJtWnZJam9pZTF3aVlYQndTV1JjSWpwY0luZDROMlZqTWpKalpUa3dORGN5WkRjMU5Gd2lMRndpZEdsdFpWTjBZVzF3WENJNlhDSXhOVFUzTkRBd016SXlYQ0lzWENKdWIyNWpaVk4wY2x3aU9sd2lOR0prTVdVNU16azNZVGcwTkRSbE5XSmtZMkpqTm1SaFl6QmpZbUkyWXpOY0lpeGNJbkJoWTJ0aFoyVmNJanBjSW5CeVpYQmhlVjlwWkQxM2VEQTVNVGt4TWpBeU5qQXpNekl4TWpGbFl6Z3dNR000T1RBeU9EQXdORFV6TmpkY0lpeGNJbk5wWjI1VWVYQmxYQ0k2WENKU1UwRmNJaXhjSW5CaGVWTnBaMjVjSWpwY0lrZEhaRVpDTm5CSlVGZFVaVkpTZUdWaVExVTFkR1JXVUV0b2NEbENUWEJPVm5JM2RXNURaRTR2VFdOMk1UWnlNMkpYVlVoRU5VOHZNVVJQTjB0T1lYUTVTRmhPVERCTmFreEphamxaWkZScU9GVXpOMFl5UmpGMmFuQm5TWFp6Y0NzeVJEZ3JiSGxtY25OTE56STVNMjFZU2psQ1lsQm1PRzR5Y2xKRGRHTjRWSFZqTldKNmEzQjVkR0oyVG5oamJ6WlRka1pHWTBwS1kwSjNPVXhwUWtWWGFVdHVObmhxSzA4dk1HWkJVM1pIVm5wMFNUVmlSalEzZHpkWFJXc3pRWEZzY3pOTFduaEhTRmswTm5VM1dUZFNlVTFZWW5SblExQkdVeTlRZEhnclUwVXpNazVpTUVKRFkzcFJkRFU0ZG5wVGNGcFdOWFJsTTNOdlJqbHNaakpUTUdGTE1VOTJWa1Y0ZWxWR1NVWXpOMGxZUkVrMmRXMURPV2xzTm1kQlRYcFdOVmg2VFVFemVrMVVTbXRaWVdJM1J5OVpkMWx4YVZOWGFXSm9NV3d5WkRJNU9VWTFiQzlWWmxGTmVtSldWM0ZHTkhFMlp6MDlYQ0o5SWl3aWIzSmtaWEpFWVhSbElqb2lNakF4T1RBMU1Ea2lMQ0owWlhKdFQzSmtTV1FpT2lKaVlUSXhOakpqTVMxbE1HTm1MVFF5TVRBdFlqUTBOeTFrTmpVME9EVTNPREJqTUdRaUxDSnRaVzFpWlhKSlpDSTZJak14TURBd01EQXhOakF3TURJNU1ESTFPQ0o5b0lJRTJqQ0NCTll3Z2dPK29BTUNBUUlDQlVFbVpTQkRNQTBHQ1NxR1NJYjNEUUVCQ3dVQU1GZ3hDekFKQmdOVkJBWVRBa05PTVRBd0xnWURWUVFLRENkRGFHbHVZU0JHYVc1aGJtTnBZV3dnUTJWeWRHbG1hV05oZEdsdmJpQkJkWFJvYjNKcGRIa3hGekFWQmdOVkJBTU1Ea05HUTBFZ1FVTlRJRTlEUVRNeE1CNFhEVEU0TURjeE56QXpNek0xTTFvWERUSXpNRGN4TnpBek16TTFNMW93Z1lReEN6QUpCZ05WQkFZVEFrTk9NUmN3RlFZRFZRUUtEQTVEUmtOQklFRkRVeUJQUTBFek1URVJNQThHQTFVRUN3d0lURzlqWVd3Z1VrRXhHVEFYQmdOVkJBc01FRTl5WjJGdWFYcGhkR2x2Ym1Gc0xURXhMakFzQmdOVkJBTU1KVEExTVVCT1UxQlBVekF3TURBd01VQk9PVEUwTkRBeE1UWk5RVFU1UVVwWFZWaFFRREV3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRRGM2NHd4L2ZlcWZnVGxMV3Z0Qmp5TGIwbVpJUzMrQWk0SzV0eUwzbmYwMmdKTHBxaXNpUDBucU5HeHE5c0EzNlBwbFFoTHpjUUk5NDBZWGVoSWEwd0p5ZEZEN3NZWmlJTVVpZ2N6endNZ3dVeTZsd3htM1hqT1RLU2V4aDMzY25jTW9CWTZsd1RFM21VZHpPVXZrU3JEQ0k2RUFMQnl0V2Z3cnlQaDE4RmlUWm5oc01sL0hhT1RVWmt1OGtYYzNtV1c3N2Nna2hzNDI5anhhTGFYV2JKcEtTc2lkcGdnSzhOdlU1Q2N4aDZzRGI1dUdtdWFnNndUSlZZS3ROTmdVSENXMTBSN0gzK05mVWdYTmFFRTN1cmVSTVFoNWU3TmxKOHJ3dy9OWVhyMkE0NlR3em95aEM3UDJoa25keWR3MTMzZ3puSlJHRVUwVVZadDh4bXFha1R0QWdNQkFBR2pnZ0Y0TUlJQmREQnNCZ2dyQmdFRkJRY0JBUVJnTUY0d0tBWUlLd1lCQlFVSE1BR0dIR2gwZEhBNkx5OXZZM053TG1ObVkyRXVZMjl0TG1OdUwyOWpjM0F3TWdZSUt3WUJCUVVITUFLR0ptaDBkSEE2THk5amNtd3VZMlpqWVM1amIyMHVZMjR2YjJOaE16RXZiMk5oTXpFdVkyVnlNQjhHQTFVZEl3UVlNQmFBRk9LMENjdk5ZYUZ6U25sLzhZcURDOTIwZm93ZE1Bd0dBMVVkRXdFQi93UUNNQUF3U0FZRFZSMGdCRUV3UHpBOUJnaGdnUnlHN3lvQkJEQXhNQzhHQ0NzR0FRVUZCd0lCRmlOb2RIUndPaTh2ZDNkM0xtTm1ZMkV1WTI5dExtTnVMM1Z6TDNWekxURTBMbWgwYlRBOUJnTlZIUjhFTmpBME1ES2dNS0F1aGl4b2RIUndPaTh2WTNKc0xtTm1ZMkV1WTI5dExtTnVMMjlqWVRNeEwxSlRRUzlqY213eE1qYzFMbU55YkRBT0JnTlZIUThCQWY4RUJBTUNCc0F3SFFZRFZSME9CQllFRkptSmpkSFpYUkdWVkRvUjdKaUlnQWtOTXFEQU1CMEdBMVVkSlFRV01CUUdDQ3NHQVFVRkJ3TUNCZ2dyQmdFRkJRY0RCREFOQmdrcWhraUc5dzBCQVFzRkFBT0NBUUVBdVZnNTlESlUvdTNMUUFpK25lTmx0djJ2dWhmejBsbDVXdTB2R3NpdU9xSkVkT1dzZGIyRmJSK1pHNGpLWkQ4UUNpSWFTckw1aWhIVWlYR05xUlU4cno5eGpuckpGaDFXLzRSQmFFSE84dGdRb1RETE5lTjdiWXF3RUJ4TXgxZlgzRUtyU0lwUFpyRHZGdFB5S1RkaHBqRVV6UVdldGk5M20xdGtSTUt6TVZTQk9KTVV3M3NHUWtCNFhmcCtNK2JDMys0S21qalhrS0JKQisxUlFGL1prZWVmUUF3YWFHbjBZeUIvWHZTSDVpWURwa0Q4Yjc0UnhxeDVBWk96VnVLMmtTZEtVRExyeUZLUnF1UUFERGtDUjRvS2VsUE1jb3ViNGJyMmN5UlFJZWpuRnJNcU9YdEYvMGtGZ1V3aDhIZXA2ZE1PYXR3aCs5cDZ0Z0x3d2x0ZDdqR0NBWXd3Z2dHSUFnRUJNR0V3V0RFTE1Ba0dBMVVFQmhNQ1EwNHhNREF1QmdOVkJBb01KME5vYVc1aElFWnBibUZ1WTJsaGJDQkRaWEowYVdacFkyRjBhVzl1SUVGMWRHaHZjbWwwZVRFWE1CVUdBMVVFQXd3T1EwWkRRU0JCUTFNZ1QwTkJNekVDQlVFbVpTQkRNQTBHQ1dDR1NBRmxBd1FDQVFVQU1BMEdDU3FHU0liM0RRRUJBUVVBQklJQkFMMmtlbkpPdTk1TEZvOUlDYmVsR05rTzNoVERpd2hrczBoTXlWaU5WdW1xQm9EYk1KSktpcTNMVXZNallZeEUxTTNJWDlnbFZBVmdiWEc4OTdIVWNBWGY0NDZjSkpaVm9uaGhHVUdmMzZlK2lCTTkvQ1ZnZWNHRklocWZCZDFzeW85TENrd0hiSmk2T1NNMUh4OTZxRVRQUHlpQ2FtOGVHNGlHL1hiejlQb2RpWnlhWlphcHpoK3RTUmZzUWFibjhPYUFBOURwTk9KMUlkcVUzdFExVXZzaUJuYmo3UWd1aFpsd0IvcC9DMFViTHRJOTBmTitFaW1HZzFGekg0MjRTQStiNmFLOXRWdGVCQmpvbXlaaGpoeFhZTWRXSWJkMVk5UThnMlFaV2FyeGdEaCszcDhoQ1pNK05mQjJVM0xpWUQwSWtPdGh0YVVvcXdMU1ZzLzc4QUE9\",\"respCode\":\"000000\"}");
//
//        orderQuery("3D123CECD42688BE","20190430");

//        statement();


//        orderQuery("111CA69B9FECBD4B-20210511151243" , "20210508");

//        qure();

        orderRefund();
    }

    public static void orderRefund() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.1");
        jsonData.addProperty("memberId", "310000016002918447");//"310000016000290258"
        jsonData.addProperty("merOperId", "SZXIANG");//"cargeer02"

        String id = "1BBE06AE130D35B9".concat("-").concat(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        System.out.println(id);
        // 汇付订单号 我们系统单号+时间戳
        jsonData.addProperty("termOrdId", "1BBE06AE130D35B9-20211027105920");

        // 汇付系统下单成功后生成的交易单号
        jsonData.addProperty("ordId", "20210915E16095672472");
        // 我们系统的订单编号
        jsonData.addProperty("outOrdId" , "1BBE06AE130D35B9");
        jsonData.addProperty("ordAmt", "88.00");

        jsonData.addProperty("transDate", "20210915");

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);

        JsonObject s = new JsonObject();
        s.add("jsonData" , jsonData);
        s.addProperty("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.cloudpnr.com/qrcp/E1106", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void qure() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.1");
        jsonData.addProperty("memberId", "310000016002909455");
        jsonData.addProperty("merOperId", "DCHONGQI");

        // 订单号
//        jsonData.addProperty("outOrdId", out_trade_no);
        jsonData.addProperty("outOrdId", "04DEC3A7DCACBA37");
//        jsonData.addProperty("termOrdId" , "9E366A7A20EB6ECD-20210916192442");
        // 订单类型 (1-正向订单2-撤销/退货订单)
        jsonData.addProperty("ordType", "2");
        // 交易日期
        jsonData.addProperty("transDate", "20210923");

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.cloudpnr.com/qrcp/P3009", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void statement() {
        JsonObject jsonData = new JsonObject();
        // 商户号
        jsonData.addProperty("memberId", "310000015000288728");    // 310000016000290258     310000016000562944
        //代理商号
        jsonData.addProperty("agentId", "310000015001987869");
        //代理商操作员号
        jsonData.addProperty("tellerId", "HXCY01");
        //结算开始日期
        jsonData.addProperty("settleStartDate", "20190201");
        //结算结束日期
        jsonData.addProperty("settleEndDate", "20190401");
        //当前页码
        jsonData.addProperty("pageNum", "1");

        jsonData.addProperty("apiVersion", "2.0.0.1");

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB7004", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void orderQuery(String orderNumber, String transDate) {
        JsonObject jsonData = new JsonObject();
        // 商户号
        jsonData.addProperty("memberId", "310000016002841517");
        // 汇付管理员账号
        jsonData.addProperty("merOperId", "DCXINHAO");

        // 订单类型 (1-正向订单2-撤销/退货订单)
        jsonData.addProperty("ordType", "2");
        // 接口版本号
        jsonData.addProperty("apiVersion", "2.0.0.1");


        // 订单号
        jsonData.addProperty("outOrdId", "78327F97659CBE88");
        // 交易日期
        jsonData.addProperty("transDate", transDate);

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.cloudpnr.com/qrcp/P3009", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downLoadBill() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("apiVersion", "2.0.0.1");
        jsonData.addProperty("memberId", "310000013000288626");
        jsonData.addProperty("transDate", "20210923");

        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.chinapnr.com/nsposmweb/webB7023", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
            // {"downloadUrl":"http://jarvisfile.oss-cn-shanghai.aliyuncs.com/app-a5dc790a-2533-4324-b15c-9ede260a497f%2F6c6afdfc-80c2-11e9-803a-0242ac11000a.zip?OSSAccessKeyId=LTAI6Yzq9tIYS57h&Signature=fQEXjGCl4cb067Bc8kXso1RtMHQ%3D&Expires=1559038660","respCode":"000000"}
            // {"downloadUrl":"http://jarvisfile.oss-cn-shanghai.aliyuncs.com/app-a5dc790a-2533-4324-b15c-9ede260a497f%2F42897f00-6866-11e9-ac28-0242ac110004.zip?OSSAccessKeyId=LTAI6Yzq9tIYS57h&Expires=1556417123&Signature=ofkvM6iuRMe3ulco6qod78L73kI%3D","respCode":"000000"}
            //{"downloadUrl":"https://file.cloudpnr.com/app-a5dc790a-2533-4324-b15c-9ede260a497f%2Fa7087c0c-1c90-11ec-ad01-0242ac120002.zip?OSSAccessKeyId=LTAI6Yzq9tIYS57h&Signature=vTPqWr%2BP%2BIq96AXN39i6ZOPVCoo%3D&Expires=1632988216","respCode":"000000"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Map<String, String> billHeader = new HashMap();

    static {
        billHeader.put("商户号", "A");
        billHeader.put("凭证号", "B");
        billHeader.put("汇付订单号", "C");
        billHeader.put("外部订单号", "D");
        billHeader.put("交易类型", "E");
        billHeader.put("交易方式", "F");
        billHeader.put("交易金额", "G");
        billHeader.put("退款金额", "H");
        billHeader.put("交易状态", "I");
        billHeader.put("交易时间", "J");
        billHeader.put("交易日期", "K");
    }

    public static List<Map> decodeBill(String filePath, Log log) {
        BufferedReader bufferedReader = null;
        InputStreamReader isr = null;
        Map<String, Integer> definedHead = null;
        try {
            isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
            bufferedReader = new BufferedReader(isr);
            List<Map> billOrder = new ArrayList<Map>();
            String tempLine = null;
            Set<String> billHeaderKey = billHeader.keySet();
            while ((tempLine = bufferedReader.readLine()) != null) {
                String[] lineArr = tempLine.split(",");
                if (definedHead == null) {
                    definedHead = new HashMap();
                    for (int i = 0; i < lineArr.length; i++) {
                        definedHead.put(lineArr[i], i);
                    }
                    continue;
                }
                Map billEntry = new HashMap();
                for (String headKey : billHeaderKey) {
                    Integer index = definedHead.get(headKey);
                    if (index != null && index >= 0 && index <= (lineArr.length - 1)) {
                        String value = lineArr[index];
                        if (value != null) {
                            value = value.trim();
                            if (value.startsWith("\"")) {
                                value = value.substring(1, value.length());
                            }
                            if (value.endsWith("\"")) {
                                value = value.substring(0, value.length() - 1);
                            }
                            value = value.trim();
                        }
                        billEntry.put(billHeader.get(headKey), value);
                    }
                }
                billOrder.add(billEntry);
            }
            return billOrder;
        } catch (Exception e) {
            log.error(e);
            return null;
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
    }

    public static List<String> unzip(String zipFilePath) {
        List<String> filePaths = new ArrayList<String>();
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));

            ZipEntry zipEntry = null;
            byte[] buffer = new byte[512];
            int readLength = 0;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {
                    continue;
                }

                File file = null;
                try {
                    file = File.createTempFile(UUID.randomUUID().toString(), ".txt");
                } catch (IOException e) {
                    return null;
                }
                filePaths.add(file.getAbsolutePath());

                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    while ((readLength = zipInputStream.read(buffer, 0, 512)) != -1) {
                        outputStream.write(buffer, 0, readLength);
                    }
                } catch (Exception e) {
                    return null;
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                        }
                    }
                }

            }
        } catch (Exception e) {
            return null;
        } finally {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e) {

                }
            }
        }
        return filePaths;


    }

    public static void unifiedOrder() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("ordAmt", new BigDecimal(1).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
        jsonData.addProperty("apiVersion", "2.0.0.1");

        // 支付方式
        jsonData.addProperty("payChannelType", "A1");
        // 微信appId
//        jsonData.addProperty("appId", "wx1036caa1f8f8a7a0");
        //   是否原生态
        jsonData.addProperty("isRaw", "1");
//        jsonData.addProperty("openId", "oafnKvr88JXngKDLxThToliPJwkU");
        jsonData.addProperty("buyerId", "2088612337736031");
        // 汇付管理员账号
        jsonData.addProperty("merOperId", "cargeer02");
        // 终端订单号
        jsonData.addProperty("termOrdId", UUID.randomUUID().toString());

        // 外部订单号
        jsonData.addProperty("outOrdId", UUID.randomUUID().toString());

        // 商品描述
        jsonData.addProperty("goodsDesc", "goodsDesc");

        // 商户号
        jsonData.addProperty("memberId", "310000016000290258");

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.cloudpnr.com/qrcp/E1101", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unifiedQrOrder() {
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("ordAmt", new BigDecimal(1).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN).toString());
        jsonData.addProperty("apiVersion", "2.0.0.1");

        // 汇付管理员账号
        jsonData.addProperty("merOperId", "cargeer02");
        // 终端订单号
        jsonData.addProperty("termOrdId", UUID.randomUUID().toString());

        // 外部订单号
        jsonData.addProperty("outOrdId", UUID.randomUUID().toString());

        // 商品描述
        jsonData.addProperty("goodsDesc", "goodsDesc");

        // 商户号
        jsonData.addProperty("memberId", "310000016000290258");
        jsonData.addProperty("payChannelType", "A1");

        // CFCA 签名
        String checkValue = SignUtil.getInstance().signByCFCA(jsonData.toString(), "asd@SDFC233");
        Map<String, String> postMap = new HashMap();
        postMap.put("jsonData", jsonData.toString());
        postMap.put("checkValue", checkValue);
        try {
            String ret = sendMapPost("https://nspos.cloudpnr.com//qrcp/E1103", postMap);
            String decodeRet = checkDataFromHfReq(ret);
            System.out.println(decodeRet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String checkDataFromHfReq(String res) throws Exception {
        boolean success = false;
        org.json.JSONObject json = new org.json.JSONObject(new String(res));
        if (json != null) {
            String respCode = json.getString("respCode");
            String respDesc = (String) json.get("respDesc");
            if ("000000".equals(respCode)) {
                String respSign = json.getString("checkValue");
                String respJsonData = json.getString("jsonData");
                // CFCA 验签
                VerifyResult verifyResult = SignUtil.getInstance().verifyByCFCA(respSign);
                if (verifyResult != null && "000".equals(verifyResult.getCode())) {
                    String decodeStr = new String(verifyResult.getContent(), SignUtil.CHARSET_UTF8);
                    return decodeStr;
                }
            }
        }
        return null;
    }

    public static String sendMapPost(String url, Map<String, String> params)
            throws UnrecoverableKeyException, KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException, IOException {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(getNoHTTPSFactory())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(null)
                    .build();
        }
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }

        HttpPost httpPost = null;
        CloseableHttpResponse httpResponse = null;
        InputStream inputStream = null;
        try {
            // 实现将请求的参数封装到表单中，即请求体中
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            // 使用post方式提交数据
            httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            // 执行post请求，并获取服务器端的响应HttpResponse
            httpResponse = httpClient.execute(httpPost);
            // 获取服务器端返回的状态码和输入流，将输入流转换成字符串
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                inputStream = httpResponse.getEntity().getContent();
                return changeInputStream(inputStream, "UTF-8");
            } else {
                httpPost.abort();
            }
        } catch (UnsupportedEncodingException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            e.printStackTrace();
        } catch (IOException e) {
            if (httpPost != null) {
                httpPost.abort();
            }
            e.printStackTrace();
        } finally {
            close(inputStream);
            close(httpResponse);
            if (httpPost != null) {
                httpPost.completed();
            }
        }
        return "";
    }

    private static void close(Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String changeInputStream(InputStream inputStream,
                                            String encode) {
        // ByteArrayOutputStream 一般叫做内存流
        ByteArrayOutputStream byteArrayOutputStream = null;
        String result = "";
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            if (inputStream != null) {
                try {
                    while ((len = inputStream.read(data)) != -1) {
                        byteArrayOutputStream.write(data, 0, len);
                    }
                    result = new String(byteArrayOutputStream.toByteArray(), encode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(byteArrayOutputStream);
        }
        return result;
    }

    public static SSLConnectionSocketFactory getNoHTTPSFactory() throws Exception {
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };
        ctx.init(null, new TrustManager[]{tm}, null);

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                ctx,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return sslsf;
    }


}
