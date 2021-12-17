package com.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/5/13 16:49
 */
@SuppressWarnings("all")
public class Main {


    public static void main(String[] args) throws InterruptedException {
//        // https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2019051364526196&scope=auth_user&redirect_uri=https://ats.kingdee.com/auto/weixin/kingdeeats/wsc/alipay/view
//        getInfo1("9800f8c4b12e4431877eb680b13aSX03");
//
//
//        // https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2019051364498294&scope=auth_user&redirect_uri=https://ats.kingdee.com/auto/weixin/kingdeeats/wsc/alipay/view
//        getInfo2("159c5963bcc34ea1a6bd53c9b4dfRX03");
//
//        // https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2019051464525368&scope=auth_user&redirect_uri=https://ats.kingdee.com/auto/weixin/kingdeeats/wsc/alipay/view
//        getInfo3("6cd025e88f2b44b3a0e5197a652cWD03");
//        createOrderNumberForHf("86769420190626092721");
// 1b=8B 1024B=1KB 1024KB=1MB 1024MB=1GB     一个汉字=2B

//        System.out.println("ok");
//        Thread.sleep(100000);


//主线程中赋值
        final ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

        final InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();

        stringThreadLocal.set("ThreadLocal string");
        stringInheritableThreadLocal.set("InheritableThreadLocal string");

//子线程中分别打印两个变量的信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(
                        Thread.currentThread().getName() + " ThreadLocal value ：" + stringThreadLocal.get());
                System.out.println(Thread.currentThread().getName() + " InheritableThreadLocal value ："
                        + stringInheritableThreadLocal.get());
            }
        }).start();

    }


    private static String get() {
        String str = null;
        try {
            str = "a";
            int a = 1 / 0;
            return str;
        } catch (Exception e) {
            str = e.getMessage();
            return str;
        } finally {
            System.out.println(str);
        }
    }

    private static void testCountDownLatch() {
        CountDownLatch c = new CountDownLatch(2);
        c.countDown();

    }

    public static String createOrderNumberForHf(String orderNumber) {
        if (StringUtils.isBlank(orderNumber)) {
            orderNumber = createOrderNumber();
        }      // 86769420190626092721
        // 86769420190626092722
        BigDecimal number = new BigDecimal(orderNumber);
        number = number.add(BigDecimal.ONE);
        return number.toString();
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String createOrderNumber() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000)).concat(simpleDateFormat.format(new Date()));
    }

    public static void getInfo1(String code) {
        String APP_ID = "2019051364526196";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCinvoSQp9kPMrkgfPpiFF2riraJD+YJbLi1tUEmr3uYBDNLrhBr4iYcE4PlHzWpUVj0LSS6jUbeWN8sXYQYM99JM0CHhgqXGvJOkR04+lrp3xn+Yqc9cqgrsaID2jz4EO4CGCb9TIa4m7f4c8h80bNjAYX0RaQYwj1nv5pBf3sMf/d8xKudHpBTfnlQn4wSucbwGRyGTg4X4F4Dqethe9mCyteUZDtCd8DARwQrZF1cpTCkRIiURdsMS+LeN7dTe2b33oMYBwJf3uwH1bfiR9wVI2nbq706n1+bE5upE4GbxQO2hUfvPO+dXu4DR4/rRvwrEH8cynZZQxC4IAoMmMxAgMBAAECggEADe7PACeDHoRy4bccncKXMDLl6wC2DiQUuoirgZR8d2L1rY62LLKMYnskxQL02zMXyeCEyB2W5T62PmW59tdEj7VKd0b6zQ5UEc6TNOyeACI+b24a6S3J6ZBv5BJl7JcnVioplNMiMtl5pGblZ0Q/qba4guvgIgjKKLkxlO/1fHn8xtqe0Ul+Ao7BjLKwLgacyDNAeo3HfpJhh81Tyc9Zf1ERDpcMGXKtgRb7O2iazDYL+UdxFCyPg3YiztRqwoM8Buc6Wyy8+1VdKlA2hkYLG9vxiastwW+Em3MSucyuIAkTxrOs+f8jwhKb+ru9j45+afDVqdVynEBACCeb9RkfzQKBgQDvl1Trht0L7KukE8GK5zrkrtxP8Ozb1ZzUAEq++a0Cd2EuWMh8o4bQhjhrOfa/WMEWFS1Z8mSq06yJ1Q7GhncrMJe0uydb6w4rUECFm64BX7ShrSsj4cO8U/DGgxFC9AIYwB9zDmhoY0ipBOtZEBYYgr2G0MaIQHYm9EZOlC/a0wKBgQCtwieJgEy8yt2CS64L6w+bn6IrII099bWt3qxbRPX/0plgM0J4nOkAuq0MGLGFzJhRzLztUdCgkeCvhDULZO1ZO05HU+hF7bwES786vj64CLwgD8AsQry0idSQf1Hw6j/x+KFjwhX1HstYOTsygJrMhELpjY4/Ic6mMGQdwkg/awKBgD3iuOydSTtrRmp7L+LRF0U6ZW3ArW2bH4cjDCuIEkgwzclK5X5MPuZdxPTtY+7I3hb2IWC6W4ZEZqyze72LvfxJ8OWd8eysKlzFveTE8Y0pS173e80o44YhqG+eKzdAD8qU1YrCjfVor7v4qVG39f0eyBWql51aCSrGreJeJH5BAoGAOkqiPOy/0a409Zsx/mmTSOAa5WD8QUcG9hd3tv7BkFs0fSN1L0VknYwCtcYq9/oVN4v57Zu+479Mo1U0/meTgJX2wtMRXzRMGRzYQHrGS7MSeacPD0osSFzc5MTx1mKfpSuF6ROZjiwQh75bb6vZQrc0Q2nryJ4s12/whlNBLZUCgYEAnPc5N7u+lSw2N6L50jjl4U9nvxU8ZnIh+Dn5G/vsVzNsu36jWEW//3rWjmKrlrMS9YCVslMbBZCzdQam7sROhOlByExzZ7PBW4t+E0yLAKxq7dtwCiDLRGfGgHxadh2hmexrp+NkdJna+f/iTY2p6HBPT7nMFgpIRBXnRpTY0QQ=";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0+GCErXjr5f+JZAY8Gk0/WKRvocs5wH9zySCaJsUjWBadgbwFlnJiV9Usudefj3u0WeURCSo+RbeTETFKDeI/bObcmsWCRxgaFeDz0R5HZHHjL1LNlMMvXyIKFSAJ87u88A4+rtfEt9tZTY2lq9sFrkCd0l/GcPQ3mMAp6aDxd+xpzxqt64tCdA35lA9Us12Yc7R/JfjpFq6EsHf7ldicmZr4h6+n7Zdv/GGllzkydAGN12LkqalAVkxyo5Dn0gRr0bnsQzRfgk8rHQDRtjJJPrHx8xZ+CtJPOJ+IiPNrZGjIfgerJh31xAq4awbMEdFZ8l2YJ16nsBFixcP1Z/C5QIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse tt = alipayClient.execute(request);
            System.out.println(APP_ID + "  getUserId=" + tt.getUserId() + "-- getAlipayUserId=" + tt.getAlipayUserId());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }

    public static void getInfo2(String code) {
        String APP_ID = "2019051364498294";
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFQNAC5ijMpINV/YrFJ5qGz7LggnHRz+K5ne0Pmm9SEwD5LDIk3syuiINntyohBHhSkasKTIhLtll4k9VlHRiO+EL+9aSzNRmF+GK8RXe0WVqFem8kTjG7lAWyl9DsE0crdDzq/pdWtfsIANoIwFaW90LW3xD083eGj0aVCxtBIg3XQuLsr28Qt8+IPZ/GWn4SUe/vrHiPYuLhoPkrUQ5AR/Nm0ok/q/W+fTsg/fGmo/uz+0kZZKrlS0UGDUIrAnQzlK+fIm68hzvsNyPR1Xexezgij+0Yrfzp374PdM/dl/RGzz15ynKAfzVq33ZNwEVKTVCt+mObpdXODhuqUvqlAgMBAAECggEAJ4EIaeOb8tdOzF5nFn3gNNk8WYyye5YazBaCoh6RpA5YOoacswuOOKvl8Qh/J9ywYyImR95ahb+glLuXBuvyTOhSzE08r5gtYec6NkCN9VyMFogujN/VpJuEeObDHEm2zq4eJTim2yEGM+A0w2VqBkV1fkgROd038R9muGbbhJpJMFw/LiczzfpevHRIYJtujJZwHDA74VDR9lXqBfH8m9/fpwI6zgr9L3saKVAmXDFx1bTDMx6em5Onqelg7+AEfh4aTvsst7Vek7yJCg5v4rY7a3jvHoFdwnYPlkZmbjSvisMU0Dacp87VJgPmX3b/VmyA5n4lOTni8jXawQqFfQKBgQDhwtRgGI7WgvypBd6hXou72y0hFXxceVSfzarVz9xCOcyg26Am3M5L1V82L/yA9Y7DwBGWlAMeXagKJKYyiYN+/FD3dMuEwMhDnRp19yQnzih1M01Nmiq+BObMc8rU+MQGjTNjEpop0NaM5Cf9KfRjP3cMsWvtPd+eMbq4mNNonwKBgQCXGfW+hk3DsKino10tfEXUaVT/Tlg3k92mxFOGsb81b8QnRlOT2/HLPXsFgKXy+Si+y7sYLULaHyxkD877zB8YqHQH5LJwkQEtLwNsX8Cpv2CKwVmKskaGvxSB+Zw9vedkpbe9UcIvfN8Tla8nZzbMDxTwvU9rB6dAPWF8aFNiOwKBgAHfvCOQEJ93NuGwSsjMIBzPFPDRXNbnquwoqOJdd1aVZD1xlUK9UnkOrFumylHHuAen8H1vfRcjb+GlrDt/KLhFH+bt8UKI1yC4jR+tjX9HFs7iPiIGxZQONlw8GdLqvbXQNjz8SotOUHma6zoOvxiTzkksr9ioStLlVsdxfU/BAoGAG/iuSUrLcoJtdjsDsLvkoWGuXmS+Z+tziL+nRrP41YePJEYciq6YoCsNbwNVtRjytf8470zRp1dF/HAAvRQYXZQxhpSLg9MUCK0/UPyYydOnp4gLb2V5MNCGl1kB11wkTblvvhg4fln7YcC/3+d3eWcBVV92KDYOfBHgzgryBKkCgYAt9lfbzo49akE3aa/qrg17KlOzfRLrkqMxNe08wvyzwE9YNZSOAxjZbxK5J5n7BySFtE57dTdR+PZMxq+2ao43wsZsFo0A2LcawhgVu3DCdEjxqi+IRjrt2VmM2PupdCHxsySEi5YAqgsx/f/rGsu2c2mo3yRjmY8APwsClBL2rg==";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnKbH9UMJAWOVskjSiznx4XuKil9O/fgXvvR/Hu7tsN12yI8veWxL9FFh24aat4AAs+GtTMGf7YlRJrjPG1wy+1VpZkkLJLUb8R94gb8feczv0Ue4Fo1qgtU/XFV97GM52o/9x4lPcxcGm6iE4AmwKiH6ul2DoidOZzv8f/Z3CdmzBdkPbKjUJ212wyMPBcds3PUd4qroDNnd4TMhgqrPmvVCwKXrDulHm5GBclUyfwHqizDbD0Jn2CG+Ag882ZPxoPpTSKcEVEG7Kt8B0Eaoa5I/k8NKAFRnIxrDiCc+ANA36gRz3S05xNmFbsKBFqGKow45jU8D1l90ne8LSIPcowIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse tt = alipayClient.execute(request);
            System.out.println(APP_ID + "  getUserId=" + tt.getUserId() + "-- getAlipayUserId=" + tt.getAlipayUserId());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }

    public static void getInfo3(String code) {
        String APP_ID = "2019051464525368";
        String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCcgIM3G7bq7pue3mook7MBvT14MRxPT1qo+WK8jCf0gTXd6bw9uiD47QHbwh3kYyohXzsin8qcpPjfavYXx5lbKefVdR0bOiun8hW1mfN+29ZST0CPVg3RzQTbqzNn/cMCKeh+dJnVMiM0WL5SEF324uhrB4VbvIN1HMJ8K6mFlQtVxaWSKSllJubzJ1by3m+Kkom4TudseJXPDFP5vu20+schkC7590s3Tu1YLdv2upBmzdYSRLhramRJAj5tio0EsAvyyvkmVpR4/iwJ3Bdf7zvkfBK+jhQOfVcGbBTLAhq0KBgFZhTrCcdeQeoUycq5AvAHG1Zphn22EOK+jHCPAgMBAAECggEAOS43QwZMQfAmSdiHvwWgDM2gzO5copmizUQ5EBzBaHNhRBBnZ8I+09R8rldxZoXY2dKan570f2FDURYajjuuS4G3I+WjWdRaKQWpau2x6rLVzsMIGpbde79FvCEEKkWsX3kCalmd7yhfdnvKJd+3BUKImrpnvknVWg/E+2tUh+qG8CLGuR4uEq1kgbGyJIryf4cpmnHZl2qiNXBwRGAMtSxr/NYgaGEwPMdQHzWFGcvj2CBGRrpRvkqbIUgqgMVJdpTihL19IjUEHNnM1kIOSsAPIEBVN+U64JDFjJrQlHGwF9pWLPOs+zdtDsKkF4ALGqbxfI3B/+4FAodcok5f6QKBgQDdnqT+AACKxj6pIO1wAWvG/ZtX1ENAY083QhD+gorZOhnvVVc9qDjaPGJOQK5tpgtGzl+zXKkC/2HU7T8z99EKtVEUUdLND0F2Dg96wCqmJ6eVw3Jgv9InBAxzBQ4c1XPJeEDtL7v6Ufw+kHvzw1YBZk48KuIn+9TbZbOam6708wKBgQC0x8xS+8nXIsGiO1F03ahWm8QMBCVwbuK54nNjWv8rJJPrFPQGV+rCo8DaVi8VXzmMq/C5UNvnczxM/ScPHHdhtBE9z0MY5xwQjXy3o0aLzNcwm8HCldaXlqYROZaImOSJLNLoQfT204zy4+hAFfGdcGfJ8WDqHYKKQ3z9c4Ts9QKBgQDVQO41OB1NbFwpdPTlXYivT6QmjKs2DxnP63Pc5BLl2sHQCofLXc9vOMHD2Pu/qRKazMVMG7LKnqIuOSkjQajWrAjcC6xUBAUSq477qGShw/C/7PVn4c1AG+Y627dryX4EdQHM+qzgv/mA3plXxDRDXoWT4hQDM5HsmF2OPZbKPwKBgQCQfYTc6qxXjEsRvLGNY8CxgIxAQmweIjKK+ejaB5gdRn4HjdbA3zIe4b05hVrajSK1QZQEuhEZO6x9qHS4ijsM11xUd70cQVPYEw4UxKaVBGMWUdebDCHZRSp0Z//eK721uiLUSitdwJZOaERwPFnCSV5bNNcOKAn3MpvLA7LBpQKBgQDZ5XMQEGhDWNDTbku8sbHyLDh3sBlR+F6mOfgPvyFYB+gGbFrcRfDgdY1UZqtBj0sWGrQM2hl3un5kVre8L5os2awmDre4ABJhskuByL85/iLOqEnvlAWThUQB/uXXkjeREw0gJFBBBarMzXgrIv6FlgcPprfgxueWb8pi4dFnZA==";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApn/9tO1XHbMs9JxJ1NxvmgDIIPBBdUvyJ7eb5DK39T1x9CXYOi7pYo5rJjM0f+00qazSRT7RPeI7o9i5+Lg6aULZ3+LfQ+1U9MesL/JRwMdiN/GGnJhoY7lchMNAx45mTq8K8sedQzP/OlajGwd50Ak7IEhRBKgVGibBRXGKqa367lCPe6loPfifeN06rw3PMMY/VPmgtUVkxWz5gN2q297zuy90rp4egyBYUSmqn3iaUgMYEYlV6GAKlC866u4FPTNoGDoeuuBmNvy1bR+SybaApYPm7w+pnnSxKZiI8S7KHwG5EgTDpPtdao7Me6YIZni7n1YHhVJIb/GAGMRMYQIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse tt = alipayClient.execute(request);
            System.out.println(APP_ID + "  getUserId=" + tt.getUserId() + "-- getAlipayUserId=" + tt.getAlipayUserId());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }

}
