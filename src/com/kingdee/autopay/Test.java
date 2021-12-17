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
//    public static String targetSn = "00000304N7NL00072359"; //Ŀ��Ψһʶ��ID��������Ϣʱʹ�� Ψһ����
    public static String srcSn = "10000000000001"; //���Ͷ�Ψһʶ��ID�Ƽ�ʹ������MAC��ַ�����豸SN��
    public static String operatorId = "3"; //��Ӫ�̱��
    public static String msg = "payment://com.newland.pospp/login?channelId \\u003dacquire\\u0026operatorNo\\u003d01";
    static NLPushApi api = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE);
    int i = 0;

    public static void main(String... args) throws Exception {

        NLPushApi nlPushApi = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE, srcSn);
        nlPushApi.bindingDevice(targetSn);//���豸
        nlPushApi.push(msgId, msgContent);//������
        nlPushApi.msgQuery(targetSn, msgId);//��ѯ���׽��

        api = NLPushApiFactory.createNlPushApi("3", RSAPRIVATE, srcSn);
        �豸����Ȩ���ȡ
        deviceBinding();
        ���ͽӿ�

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

        ��Ϣ��ѯ
        received(msgId);


    }

    public static String push() throws Exception {
        */
/**
         * �÷��������������Ϣ���豸(POS��)������Ϣ
         * @param targDevSn �󶨽ӿ������ɵ�targetSn
         * @param msg ��Ҫ���͵�����
         * @return ��ϢΨһʶ��ID
         *//*

        String msgId = api.push(targetSn, msg);
        System.out.println("======>" + msgId);
        return msgId;
    }

    public static void received(String msgId) throws Exception {
        */
/**
         * �ýӿ����ڲ�ѯ"push()"���������͵���Ϣ��״̬��״̬ͨ��state����
         *          00:��Ϣ�ѷ���   --  �������Ѿ�������Ϣ������֤�ͻ����յ�
         *          01:������Ϣ���豸(POS��)�ɹ�������Ϣ
         *          02:������Ϣ���豸(POS��)�����ɹ����أ�handlemsgΪ��������
         *          03:����Ϣ������
         *          04:������Ϣ���豸(POS��)æ�����ڴ���������Ϣ���ܾ�����������
         * @param msgId msgIdΪ��Ҫ��ѯ����ϢIDche
         * @param targDevSn       �󶨽ӿ������ɵ�targetSn
         * @return ��Ϣ״̬
         *//*

        BeanQuery beanQuery = api.msgQuery(targetSn, msgId);
        System.out
                .println("======>state=" + beanQuery.state + "  handlemsg=" + beanQuery.handlemsg);
    }

    public static String deviceBinding() throws Exception {
        */
/**
         * �÷������ڻ�ȡ�豸����Ȩ��
         * @param targDevSn Ψһʶ�������Ϣ���豸(POS��)���ɣ��Ƽ�ʹ��UUID���豸�󶨳ɹ���ͨ��  targetSn �������Ϣ���豸������Ϣ����Ҫ�û����б���
         * @return �豸����Ȩ��
         *//*

        String authCode = api.bindingDevice(targetSn);
        System.out.println("======>" + authCode);

        return authCode;
    }
}*/
