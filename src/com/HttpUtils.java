package com;

import com.alibaba.fastjson.JSONObject;
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

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shaungfeng_li
 * Date: 2017/12/6
 * Time: 15:00
 */
@SuppressWarnings("unchecked")
public class HttpUtils {
    public static void main(String[] args) {
        ConcurrentHashMap cm = new ConcurrentHashMap();
        HashMap m =new HashMap();
        for (int i = 0; i < 2000; i++) {
            cm.put(i+"" , UUID.randomUUID().toString());
            m.put(i+"" , UUID.randomUUID().toString());
        }


        System.out.println("");
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
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (String key : parameter.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, String.valueOf(parameter.get(key))));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            String responseStr = EntityUtils.toString(entity, "UTF-8");
            return responseStr;
        } finally {
            close(httpPost, entity, response);
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    public static void close(HttpPost httpPost, HttpEntity entity, CloseableHttpResponse response) {
        try {
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpPost != null) {
            httpPost.completed();
        }
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取Web_access_tokenhttps的请求地址
    public static String WEB_ACCESS_TOKENHTTPS = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
