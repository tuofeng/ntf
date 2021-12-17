package com.kingdee.autopay;

import com.newland.nlpush.sdk.java.NLPushApi;
import com.newland.nlpush.sdk.java.NLPushApiFactory;
import com.newland.nlpush.sdk.java.bean.BeanQuery;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/11 19:05
 */
public class HfPay {
    public static final String RSAPRIVATE =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIUTaplPv6C2KVuVv0u+wU6RWfk" +
                    "/3uz3anAfhMlpKfkiF6JhHvyGDkRwkP" +
                    "+6koOcq86PlGCV9nXmJV0fs1k1MeYbbk7IKGji2AMyg7REVed7PMjNiDhfelYrUBTigPSeRdNMBPf2VQrqrw9JCs9T4iVgHZ2qev3l7Kvs1QkdR7AjAgMBAAECgYB2dcFHtmDsDBwmxdei8qybZvJD2WJ1aQ1bwALxU+ckOf/18SSnQCJFNDsvGgNAm1+pIdDAZd4eN3/9NLHvcFX4nwa8jNjQxupbQ3pcpKJL9mrzdql0ycSUS5mI5aoJ0THvH9crBta3q8fnSUqzkSj3AywSOugmgeyGvaU9xiz/uQJBAPD+MrNrPSdU3kh0ya5fWTTlypuRTn9Y73hDQ4uDWhzHIjj1G0JepQ8efUwgdnQrBNM2EuiWHGQk3cvQV1GvVG0CQQCNXNobaWX9nD5co4ZVkT1Zj23theCbVN6UD83LFoxLxrwf8ttoj+bYUuGlJM+gYQARmgKwnKQ0SSNyagPTUJzPAkEAnAJTXCceLaoZRBqmvUVogIAKC2+ju3kdfWM+BMEBwwN+uhSikvKmNAVu46tYQ5fdxcWJtBwJQSNEmj7DaFPyeQJAIUe73Xo51bQkBmFFLf3siJo40hOTpl4brJXv6CEd1HUsMwVU3FAMk98nWl7JNsO/ZWVxvqySB/E0FCqbTfOLhwJBAN8OWlC/81GKD+83yiYa4C63GNBr+S/iZrVQm5IAfwopZaBgGlx5WR224yaFRX/Q4WkIS79c1+t2GCZaICpTRJM=";
    //��Ӫ�̱��
    public static String operatorId = "3";
    public static String targetSn = "8F3DB0D257581442AF18";// "B9054F749BE9C0A0D03D";
    NLPushApi nlPushApi = NLPushApiFactory.createNlPushApi(operatorId, RSAPRIVATE);


    public HfVerifyResult doBusiness(String parameter) {
        System.out.println("parameter:" + parameter);
        try {
            String msgId = nlPushApi.push(targetSn, parameter);
            System.out.println("msgId=" + msgId);
            System.out.println(">>>>>>>>>>>" + new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
            doBusinessQuery(msgId);
            Thread.sleep(2000);
            if (msgId != null && msgId.length() > 0){
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(2000);
                    System.out.println(">>>>>>>>>>>"+new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
                    doBusinessQuery(msgId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String doPost(String url, Map<String, Object> parameter) throws Exception {
        CloseableHttpClient httpClient = null;
        if (url.startsWith("https")) {
            SSLContext sslcontext = null;
            try {
                sslcontext = SSLContext.getInstance("SSLv3");
                sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }}, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpClient = HttpClients.custom().setSSLContext(sslcontext).
                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : parameter.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, String.valueOf(parameter.get(key))));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity, "UTF-8");
            return responseStr;
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    public BeanQuery doQuery(String psMsgID) throws Exception {
        return nlPushApi.msgQuery(targetSn, psMsgID);
    }

    public HfVerifyResult doBusinessQuery(String psMsgID) {
        try {
            BeanQuery beanQuery = nlPushApi.msgQuery(targetSn, psMsgID);
            System.out.println("@@@@@@@@@@@@@@@@@@@ state=" + beanQuery.state.toString() + "  handlemsg=" + beanQuery.handlemsg);
            s(beanQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void s(BeanQuery beanQuery) {
        try {
            String errorMsg = null;
            if (beanQuery != null) {
                if (beanQuery.state != null && beanQuery.state.length() > 0) {
                    if ("04".equals(beanQuery.state)) {
                        errorMsg = "正忙";
                    }
                }
                if ((errorMsg == null || errorMsg.length() == 0) && (beanQuery.handlemsg != null && beanQuery.handlemsg.length() > 0) && beanQuery.handlemsg.contains("responseCode")) {
                    // {"message":"交易操作员不能为空","responseCode":"PE"}
                    JSONObject jsonObject = new JSONObject(beanQuery.handlemsg);
                    if (jsonObject != null) {
                        String responseCode = jsonObject.getString("responseCode");
                        if ("TF".equals(responseCode) || "PE".equals(responseCode) || "UF".equals(responseCode)) {
                            String message = jsonObject.getString("message");
                            if (message != null && message.length() > 0) {
                                errorMsg = message;
                            }
                        }
                    }
                }

            }
            System.out.println(errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String doBindDevice() {
        String code = null;
        try {
            code = nlPushApi.bindingDevice(targetSn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


/*

    */
/**
 * @param pInData     �������  payment://com.pnr.pospp/payment?channelId=acquire&ordAmt=1....
 * @param quertyTimes ��ѯʱ��
 * @param outStr      �������� 2�����ȵ����飬�����һ��ֵ�ǽ��׽�����ڶ���ֵ�����ڽ��ײ�ѯʱʹ�õ�psMsgID
 * @return
 *//*

    private native int posTrans(String pInData, int quertyTimes, String[] outStr);

    */
/**
 * @param psMsgID POS�������ص�msgID
 * @param outStr  �������� 1�����ȵ����� ��ѯ���صĽ��׽��
 * @return
 *//*

    private native int posTransQuery(String psMsgID, String[] outStr);

    */
/**
 * @param outStr �������� 1�����ȵ����� ���󶨵���Ȩ��
 * @return
 *//*

    private native int posBindDevice(String[] outStr);

    static {
        System.loadLibrary("autoPay");
    }

    public HfVerifyResult doBusiness(String parameter) {
        //TODO quertyTimes
        int quertyTimes = 20;
        String[] outStr = new String[2];
        int code = posTrans(parameter, quertyTimes, outStr);
        boolean success = false;
        JSONObject jsonObject = null;
        if (code >= 0) {
            success = true;
            if (outStr[0] != null && outStr[0].length() > 0) {
                try {
                    jsonObject = new JSONObject(outStr[0]);
                    jsonObject.put("BUSINESS_QUERY_ID", outStr[1]);
                } catch (JSONException j) {
                }
            }
        }
        return new HfVerifyResult(success, code, jsonObject, outStr[0]);
    }

    public HfVerifyResult doBusinessQuery(String psMsgID) {
        String[] outStr = new String[1];
        int code = posTransQuery(psMsgID, outStr);
        boolean success = false;
        JSONObject jsonObject = null;
        if (code >= 0) {
            success = true;
            if (outStr[0] != null && outStr[0].length() > 0) {
                try {
                    jsonObject = new JSONObject(outStr[0]);
                } catch (JSONException j) {
                }
            }
        }
        return new HfVerifyResult(success, code, jsonObject, outStr[0]);
    }

    public String doBindDevice() {
        String[] outStr = new String[1];
        int code = posBindDevice(outStr);
        if (code >= 0) {
            return outStr[0];
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        HfPay hfPay = new HfPay();

//        String[] outStr = new String[2];
//
//        int code = hfPay.posTrans("", 1, outStr);
//        System.out.println("code=" + code + "  outStr[0]= " + outStr[0] + " outStr[1]= " + outStr[1]);
//
        String[] outStr1 = new String[1];
        int code2 = hfPay.posTransQuery("03843267872753471684815313950391", outStr1);
        System.out.println("code=" + code2 + "  outStr[0]= " + outStr1[0]);


//        String[] outStr3 = new String[1];
//        int code3 = hfPay.posBindDevice(outStr3);
//        System.out.println("code3=" + code3 + "  outStr[0]= " + outStr3[0]);
    }
*/


}


