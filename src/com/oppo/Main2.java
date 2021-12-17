package com.oppo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/3/10 14:06
 * "Cookie", "UM_distinctid=16e49f629cf81f-04ecfe87f514cb-4f4f082e-1fa400-16e49f629d069b; SLARDAR_WEB_ID=6811558412; passport_auth_status=28e49dbd224c849b8b159b4d625709f5%2C5694d8e65a5b4b0ae4575ad083a730f6; sid_guard=8530e2c2463f67681ee355a2e8b2e463%7C1583819561%7C5184000%7CSat%2C+09-May-2020+05%3A52%3A41+GMT; uid_tt=b1ba5c8e2797d4bf3e243f450107684f; uid_tt_ss=b1ba5c8e2797d4bf3e243f450107684f; sid_tt=8530e2c2463f67681ee355a2e8b2e463; sessionid=8530e2c2463f67681ee355a2e8b2e463; sessionid_ss=8530e2c2463f67681ee355a2e8b2e463; motor_dealer_session_id=ea9b9761c4a54e962f83d6fe4042cbc3; tt_webid=; dealer_id="
 */
public class Main2 {

    public static void main(String[] args) {
//        CookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();
//        try {
//            doLogin(
//                    httpClient,
//                    "https://sso.toutiao.com/quick_login/v2/?aid=24&service=%2F%2Fmct.dcdapp.com%2Flogin-callback-v2%2F&account_sdk_source=sso&mix_mode=1&fp=verify_k7mpzxjm_j8pbWEbv_wI4Y_4JTL_9eGv_v7ccvaQ9sYeo&code=3733323d&mobile=34363234343c373c363c31&web_timestamp=1583910409215&_signature=hoZwwAAAAABtk5dLIFUZNYaGcNAANj6"
//                    , cookieStore);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //sdf.parse(sdf.format(Calendar.getInstance().getTime()))
        int iCount = 1000;
        int pageCount = 1000;
        int loopCount = (iCount / pageCount) + (iCount % pageCount == 0 ? 0 : 1);

        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.MONTH));

        c.set(Calendar.MONTH , 1);

        System.out.println(c.get(Calendar.MONTH));

    }

    public static SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            SSLContexts.createDefault(),
            new String[]{"TLSv1"},
            null,
            null);

    public static void doLogin(CloseableHttpClient httpClient, String url, CookieStore cookieStore) throws Exception {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(getDeaultRequestConfig());
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");

            response = httpClient.execute(httpGet);

            String cookie = toCookie(response.getHeaders("Set-Cookie"));
            System.out.println("doLogin cookie:" + cookie);

            entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("doLogin result: " + result);

            JSONObject jsonObject = new JSONObject(result);
            String callUrl = jsonObject.getString("redirect_url");
            doLoginCallback(httpClient, callUrl, cookie, cookieStore);

        } finally {
            close(httpGet, entity, response);
        }
    }

    public static void doLoginCallback(CloseableHttpClient httpClient, String url, String loginCookie, CookieStore cookieStore) throws Exception {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(getDeaultRequestConfig());
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");
            httpGet.addHeader("Cookie", loginCookie);

            response = httpClient.execute(httpGet);

            StrBuilder cookieBuild = new StrBuilder();
            for (Cookie cookie : cookieStore.getCookies()) {
                if (cookie == null || StringUtils.isBlank(cookie.getName())) {
                    continue;
                }
                cookieBuild.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
            System.out.println("doLoginCallback cookie:" + cookieBuild.toString());

            entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("doLoginCallback result:" + result);

            getData(cookieBuild.toString());

        } finally {
            close(httpGet, entity, response);
        }
    }

    private static void getData(String cookie) throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        String result = doGet(
                httpClient,
                "https://mct.dcdapp.com/source/list?start_time=1583510400&end_time=1583942399&offset=0&limit=20",
                toMap("Cookie", "passport_auth_status=28e49dbd224c849b8b159b4d625709f5%2C5694d8e65a5b4b0ae4575ad083a730f6; sid_guard=8530e2c2463f67681ee355a2e8b2e463%7C1583819561%7C5184000%7CSat%2C+09-May-2020+05%3A52%3A41+GMT; uid_tt=b1ba5c8e2797d4bf3e243f450107684f; uid_tt_ss=b1ba5c8e2797d4bf3e243f450107684f; sid_tt=8530e2c2463f67681ee355a2e8b2e463; sessionid=8530e2c2463f67681ee355a2e8b2e463; sessionid_ss=8530e2c2463f67681ee355a2e8b2e463; motor_dealer_session_id=ea9b9761c4a54e962f83d6fe4042cbc3;")
        );
        System.out.println(result);
    }

    //  passport_auth_status sessionid sessionid_ss sid_guard sid_tt sso_auth_status sso_uid_tt sso_uid_tt_ss toutiao_sso_user toutiao_sso_user_ss uid_tt uid_tt_ss
    private static String toCookie(Header[] headers) {
        if (headers == null) {
            return "";
        }
        StrBuilder str = new StrBuilder();
        for (Header header : headers) {
            if (header == null || StringUtils.isBlank(header.getValue())) {
                continue;
            }
            String value[] = header.getValue().trim().split(";");
            if (value != null && value.length > 0) {
                if (StringUtils.isBlank(value[0])) {
                    continue;
                }
                str.append(value[0]).append("; ");
            }
        }
        String cookie = str.toString();
        if (cookie.length() > 0) {
            cookie = cookie.substring(0, cookie.length() - 1);
        }
        return cookie;
    }

    public static Map<String, Object> toMap(Object... values) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i += 2) {
            map.put(String.valueOf(values[i]), values[i + 1]);
        }
        return map;
    }

    public static String doPost(CloseableHttpClient httpClient, String url, Map<String, Object> headers, HttpEntity httpEntity) throws Exception {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(getDeaultRequestConfig());
            if (headers == null) {
                headers = toMap("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36");
            }
            Iterator<Map.Entry<String, Object>> entries = headers.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> ent = entries.next();
                httpPost.addHeader(ent.getKey(), ent.getValue() == null ? "" : ent.getValue().toString());
            }
            if (httpEntity != null) {
                httpPost.setEntity(httpEntity);
            }

            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            close(httpPost, entity, response);
        }
    }

    public static RequestConfig getDeaultRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(5000).setConnectionRequestTimeout(10000).build();
    }

    public static void close(HttpGet httpGet, HttpEntity entity, CloseableHttpResponse response) {
        try {
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpGet != null) {
            httpGet.completed();
        }
        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doGet(CloseableHttpClient httpClient, String url, Map<String, Object> headers) throws Exception {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(getDeaultRequestConfig());
            if (headers != null) {
                Iterator<Map.Entry<String, Object>> entries = headers.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, Object> ent = entries.next();
                    httpGet.addHeader(ent.getKey(), ent.getValue() == null ? "" : ent.getValue().toString());
                }
            }

            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            close(httpGet, entity, response);
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
}
