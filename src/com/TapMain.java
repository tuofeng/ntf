package com;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * @author: shuangfeng_li 2021/12/9 13:47
 */
public class TapMain {

    public static void main(String[] args) {

        try {
            String ret = doGet(0, 15);
            JSONObject json = new JSONObject(ret);
            System.out.println(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String doGet(int limit, int form) throws Exception {
        String url = "https://www.taptap.com/webapiv2/app-top/v1/hits?platform=android&type_name=reserve&X-UA=V%3D1%26PN%3DWebApp%26LANG%3Dzh_CN%26VN_CODE%3D53%26VN%3D0.1.0%26LOC%3DCN%26PLT%3DPC%26DS%3DAndroid%26UID%3D110dfa8a-5817-4836-a653-f4abe2163280%26DT%3DPC"
                .concat("&limit=").concat(String.valueOf(limit)).concat("&from=").concat(String.valueOf(form));

        CloseableHttpClient hc = HttpClients.custom().setSSLSocketFactory(SpiderUtil.sslsf).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getDeaultRequestConfig());
        CloseableHttpResponse response = null;
        HttpEntity httpEntity = null;
        InputStream inputStream = null;
        ByteArrayOutputStream output = null;
        try {
            response = hc.execute(httpGet);
            httpEntity = response.getEntity();
            String responseStr = EntityUtils.toString(httpEntity);
            return responseStr;
        } finally {
            SpiderUtil.close(httpGet, httpEntity, response);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (hc != null) {
                hc.close();
            }
        }
    }

    public static RequestConfig getDeaultRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(5000).setConnectionRequestTimeout(10000).build();
    }

}
