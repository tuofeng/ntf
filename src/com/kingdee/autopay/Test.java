/*
package com.kingdee.autopay;

import com.newland.nlpush.sdk.java.NLPushApi;
import com.newland.nlpush.sdk.java.NLPushApiFactory;
import com.newland.nlpush.sdk.java.bean.BeanQuery;

*/
/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/13 10:14
 *//*

public class Test {
    public static final String RSAPRIVATE =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIUTaplPv6C2KVuVv0u+wU6RWfk" +
                    "/3uz3anAfhMlpKfkiF6JhHvyGDkRwkP" +
                    "+6koOcq86PlGCV9nXmJV0fs1k1MeYbbk7IKGji2AMyg7REVed7PMjNiDhfelYrUBTigPSeRdNMBPf2VQrqrw9JCs9T4iVgHZ2qev3l7Kvs1QkdR7AjAgMBAAECgYB2dcFHtmDsDBwmxdei8qybZvJD2WJ1aQ1bwALxU+ckOf/18SSnQCJFNDsvGgNAm1+pIdDAZd4eN3/9NLHvcFX4nwa8jNjQxupbQ3pcpKJL9mrzdql0ycSUS5mI5aoJ0THvH9crBta3q8fnSUqzkSj3AywSOugmgeyGvaU9xiz/uQJBAPD+MrNrPSdU3kh0ya5fWTTlypuRTn9Y73hDQ4uDWhzHIjj1G0JepQ8efUwgdnQrBNM2EuiWHGQk3cvQV1GvVG0CQQCNXNobaWX9nD5co4ZVkT1Zj23theCbVN6UD83LFoxLxrwf8ttoj+bYUuGlJM+gYQARmgKwnKQ0SSNyagPTUJzPAkEAnAJTXCceLaoZRBqmvUVogIAKC2+ju3kdfWM+BMEBwwN+uhSikvKmNAVu46tYQ5fdxcWJtBwJQSNEmj7DaFPyeQJAIUe73Xo51bQkBmFFLf3siJo40hOTpl4brJXv6CEd1HUsMwVU3FAMk98nWl7JNsO/ZWVxvqySB/E0FCqbTfOLhwJBAN8OWlC/81GKD+83yiYa4C63GNBr+S/iZrVQm5IAfwopZaBgGlx5WR224yaFRX/Q4WkIS79c1+t2GCZaICpTRJM=";
//    public static String targetSn = "00000304N7NL00072359"; //目标唯一识别ID，发送消息时使用 唯一即可
    public static String srcSn = "10000000000001"; //发送端唯一识别ID推荐使用网卡MAC地址或者设备SN号
    public static String operatorId = "3"; //运营商编号
    public static String msg = "payment://com.newland.pospp/login?channelId \\u003dacquire\\u0026operatorNo\\u003d01";
    static NLPushApi api = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE);
    int i = 0;

    public static void main(String... args) throws Exception {

        NLPushApi nlPushApi = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE, srcSn);
        nlPushApi.bindingDevice(targetSn);//绑定设备
        nlPushApi.push(msgId, msgContent);//发起交易
        nlPushApi.msgQuery(targetSn, msgId);//查询交易结果

        api = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE, srcSn);
        设备绑定授权码获取
        deviceBinding();
        推送接口

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        try {
                            String msgId = push();
                            i++;
                            System.out.println(i + "-----");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        消息查询
        received(msgId);


    }

    public static String push() throws Exception {
        */
/**
         * 该方法用于向接收消息端设备(POS机)推送消息
         * @param targDevSn 绑定接口中生成的targetSn
         * @param msg 需要推送的内容
         * @return 消息唯一识别ID
         *//*

        String msgId = api.push(targetSn, msg);
        System.out.println("======>" + msgId);
        return msgId;
    }

    public static void received(String msgId) throws Exception {
        */
/**
         * 该接口用于查询"push()"方法中推送的消息的状态，状态通过state返回
         *          00:消息已发送   --  服务器已经发送消息，不保证客户端收到
         *          01:接收消息端设备(POS机)成功接收消息
         *          02:接收消息端设备(POS机)处理后成功返回，handlemsg为返回内容
         *          03:该消息不存在
         *          04:接收消息端设备(POS机)忙，正在处理其他消息，拒绝处理本次推送
         * @param msgId msgId为需要查询的消息IDche
         * @param targDevSn       绑定接口中生成的targetSn
         * @return 消息状态
         *//*

        BeanQuery beanQuery = api.msgQuery(targetSn, msgId);
        System.out
                .println("======>state=" + beanQuery.state + "  handlemsg=" + beanQuery.handlemsg);
    }

    public static String deviceBinding() throws Exception {
        */
/**
         * 该方法用于获取设备绑定授权码
         * @param targDevSn 唯一识别接收消息端设备(POS机)即可，推荐使用UUID，设备绑定成功后通过  targetSn 向接收消息端设备推送消息，需要用户自行保存
         * @return 设备绑定授权码
         *//*

        String authCode = api.bindingDevice(targetSn);
        System.out.println("======>" + authCode);

        return authCode;
    }
}*/
