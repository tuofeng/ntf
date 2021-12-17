package com.oppo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/7/15 14:25
 */
public class ZkMain {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        for (int i = 0; i < 100; i++) {
            try {
                doLogin(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void doLogin(CloseableHttpClient httpClient) throws Exception {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet("http://localhost:5233/test/zookeeperLock");

            response = httpClient.execute(httpGet);

            entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);

        } finally {
            close(httpGet, entity, response);
        }
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

}
