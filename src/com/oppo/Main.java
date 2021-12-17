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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/3/10 14:06
 * "Cookie", "UM_distinctid=16e49f629cf81f-04ecfe87f514cb-4f4f082e-1fa400-16e49f629d069b; SLARDAR_WEB_ID=6811558412; passport_auth_status=28e49dbd224c849b8b159b4d625709f5%2C5694d8e65a5b4b0ae4575ad083a730f6; sid_guard=8530e2c2463f67681ee355a2e8b2e463%7C1583819561%7C5184000%7CSat%2C+09-May-2020+05%3A52%3A41+GMT; uid_tt=b1ba5c8e2797d4bf3e243f450107684f; uid_tt_ss=b1ba5c8e2797d4bf3e243f450107684f; sid_tt=8530e2c2463f67681ee355a2e8b2e463; sessionid=8530e2c2463f67681ee355a2e8b2e463; sessionid_ss=8530e2c2463f67681ee355a2e8b2e463; motor_dealer_session_id=ea9b9761c4a54e962f83d6fe4042cbc3; tt_webid=; dealer_id="
 */
public class Main {

    public enum SmsTemplateDynamicParam {

        USER_NAME("${姓名}"),
        USER_NAME2("${姓a名垫付}"),
        USER_SEX("${性别}");

        private String value;


        public String getValue() {
            return value;
        }

        SmsTemplateDynamicParam(String value) {
            this.value = value;
        }
    }

    public static List<SmsTemplateDynamicParam> toTemplateDynamicParam(String smsContent) {
        List<SmsTemplateDynamicParam> list = new ArrayList<>();
        if (StringUtils.isBlank(smsContent)) {
            return list;
        }
        char[] cha = smsContent.toCharArray();
        for (int i = 0; i < cha.length; i++) {
            if (cha[i] == '$'){
                for (SmsTemplateDynamicParam enu : SmsTemplateDynamicParam.values()) {
                    int end = i+enu.getValue().length();
                    if (smsContent.length() >= end){
                        String str = smsContent.substring(i , end);
                        if (str.equals(enu.getValue())){
                            list.add(enu);
                        }
                    }
                }
            }
        }
        return list;
    }
    public static void main(String[] args) throws ParseException {
        Date createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-05-08 19:23:00");
        int dayCount = (int) ((Calendar.getInstance().getTime().getTime() - createTime.getTime()) / (24 * 60 * 60 * 1000));


        toTemplateDynamicParam("a${姓名}");
        toTemplateDynamicParam("DNF角色${姓名}${姓名}d${姓a名垫付}d${性别}${性d别}fgdgf  fgdfkl国际快递费${姓a名垫付}${姓名");


//        CookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();
//        try {
//            doLogin(
//                    httpClient,
//                    "https://sso.toutiao.com/quick_login/v2/?aid=24&service=%2F%2Fmct.dcdapp.com%2Flogin-callback-v2%2F&account_sdk_source=sso&mix_mode=1&code=33303c33&mobile=343d3637353c303631363c&web_timestamp=1583918412149&_signature=cDj6xAAAAACbLR1Poky2SXA4-tAAC5T"
//                    , cookieStore);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            sendSms();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        BigDecimal a = new BigDecimal("0.01");
//        BigDecimal dis = new BigDecimal("9.5");
//        System.out.println(
//                new BigDecimal("0.01").multiply(new BigDecimal(4.9).multiply(new BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_HALF_UP)
//        );


//        Semaphore semaphore = new Semaphore(5);
//        try {
//            semaphore.acquire();
//            semaphore.getQueueLength();
//            semaphore.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        AtomicBoolean b = new AtomicBoolean();
        boolean success = b.compareAndSet(false, true);
        b.getAndSet(false);

        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();

//        AtomicIntegerFieldUpdater<Object> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Object.class , "name");
//        fieldUpdater.decrementAndGet(0);


//        AtomicIntegerArray array = new AtomicIntegerArray(2);
//        array.getAndSet(0,2);
//        array.getAndSet(1,3);
//        array.addAndGet(0,4);
//        System.out.println("a");

        HashMap m = new HashMap();
        m.put("a", "1");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int j = 0; j < 20; j++) {
            concurrentHashMap.put(j, i);
        }
//        concurrentHashMap.computeIfAbsent()
        System.out.println();
//        "ss".hashCode()

//        String s = "abc的大股东1";
//        CopyOnWriteArrayList c = new CopyOnWriteArrayList();
//        c.get();
//        c.add();
//
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.writeLock().lock();
//
//        ArrayList a = new ArrayList();
//        a.add();
//        a.remove();
//
//        Executors.newScheduledThreadPool();
//        Executors.newCachedThreadPool();
//        Executors.newFixedThreadPool();
//        Executors.newSingleThreadExecutor();


    }

    public static int countSubString(String string,String subString){
        // 定义一个count来存放字符串出现的次数
        int count = 0 ;
        // 调用String类的indexOf(String str)方法，返回第一个相同字符串出现的下标
        while (string.indexOf(subString) != -1){
            count ++ ;
            int index = string.indexOf(subString);
            //将长的那个字符串做截取
            string = string.substring(index+1);
        }
        return count;
    }

    //创建原始任务
    ForkJoinPool pool = new ForkJoinPool(5);
    long ret = pool.invoke(new MyTask(0, 1000000));
//    pool.shutdown();

    public static class MyTask extends RecursiveTask<Long> {
        private int start, end;
        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start > 10000) {
                MyTask t1 = new MyTask(start, start + 10000);
                t1.fork(); // fork()子任务

                MyTask t2 = new MyTask(start + 10001, end);
                t2.fork();

                return t1.join() + t2.join(); // 取得子任务运行结果
            } else {
                long ret = 0;
                for (int i = start; i <= end; i++) {
                    ret += i;
                }
                return ret;
            }
        }
    }

    public static String secToTime(int time) {
        try {
            String timeStr = null;
            int hour = 0;
            int minute = 0;
            int second = 0;
            if (time <= 0) {
                return "0";
            } else {
                minute = time / 60;
                if (minute < 60) {
                    second = time % 60;
                    if (minute <= 0) {
                        return String.valueOf(second).concat("秒");
                    } else {
                        return String.valueOf(minute).concat("' ").concat(String.valueOf(second)).concat("''");
                    }
                } else {
                    hour = minute / 60;
                    minute = minute % 60;
                    second = time - hour * 3600 - minute * 60;
                    return String.valueOf(hour).concat("时") + String.valueOf(minute).concat("分") + String.valueOf(second).concat("秒");
                }
            }
        } catch (Exception e) {
            return null;
        }
    }


    public static void sendSms() throws Exception {
        String mobile = "18320951111";
        String timeStamp = "1583917313937";
        String signature = "cDj6xAAAAACbLR1Pokyy3HA4-tAAC5T";
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        StringBuilder url = new StringBuilder("https://sso.toutiao.com/send_activation_code/v2/");
        url.append("?aid=24&service=%2F%2Fmct.dcdapp.com%2Flogin-callback-v2%2F&account_sdk_source=sso&type=24&mobile=")
                .append(mobile).append("&web_timestamp=").append(timeStamp).append("&_signature=").append(signature);
        String responseStr = doGet(
                httpClient,
                url.toString(),
                toMap("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0",
                        "Accept", "text/javascript, text/html, application/xml, text/xml, */*",
                        "Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2",
                        "Accept-Encoding", "gzip, deflate, br",
                        "Content-Type", "application/x-www-form-urlencoded",
                        "Connection", "keep-alive",
                        "Referer", "https://sso.toutiao.com/login/?service=//mct.dcdapp.com/login-callback-v2/"));
        System.out.println(responseStr);
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
