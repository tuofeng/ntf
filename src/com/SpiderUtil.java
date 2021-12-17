package com;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2018/3/22 11:01
 */
public class SpiderUtil {

    public static SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            SSLContexts.createDefault(),
            new String[]{"TLSv1"},
            null,
            null);

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
