package com.kingdee.autopay;

import com.kingdee.autopay.dto.*;
import com.newland.nlpush.sdk.java.bean.BeanQuery;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 16:10
 */
public class Main {
    // pay MerOrdId=60467020190522114217
    // refund MerOrdId=65435620190522114328
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    // 21291120190528184254
    public static void main(String[] args) throws Exception {


        HfPayService hfPayService = new HfPayServiceImpl();
        pay(hfPayService);
//        doQuery("40451519752021765773914217158164");


//        refund(hfPayService , "66721920190626113133");
//        doQuery("40451519752021765773914217158164");
//
//        cancel(hfPayService , "411782");
//        doQuery("08073756622305803390433294436386");
//
//        System.out.println(valid("1000,236661254,3dsfs"));
    }

    public static boolean onlinePay() {
        return false;
    }

    public static BigDecimal randomAmount(BigDecimal limitAmount) {
        if (limitAmount == null || limitAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        int amount = new Random().nextInt(limitAmount.multiply(new BigDecimal(100)).intValue());
        BigDecimal ret = new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
        if (ret.compareTo(limitAmount) > 0) {
            return limitAmount;
        }
        if (ret.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        return ret;
    }

    private static void bind() {
        HfPay hfPay = new HfPay();
        hfPay.doBindDevice();
    }

    private static void doQuery(String msgId) throws Exception {
        HfPay hfPay = new HfPay();
        BeanQuery beanQuery = hfPay.doQuery(msgId);
        System.out.println(beanQuery.toString());
    }

    private static void login(HfPayService hfPayService) {
        HfLoginDto loginDto = new HfLoginDto();
        loginDto.setEmpAccount("cargeer02");
        loginDto.setEmpPass("9deBgaaF");
        HfVerifyResult verifyResult2 = hfPayService.login(loginDto);
        verifyResult2.toString();
    }

    private static void logOut(HfPayService hfPayService) {
        HfLogOutDto logOutDto = new HfLogOutDto();
        HfVerifyResult verifyResult1 = hfPayService.logout(logOutDto);
        verifyResult1.toString();
    }

    private static void pay(HfPayService hfPayService) {

        HfPaymentDto payment = new HfPaymentDto();
        payment.setMemberId("310000016002279231");
        payment.setChannelId(HfChannelType.scan);
//        payment.setMobilePayType(HfMobilePayType.A);
        payment.setOrdAmt("3");
        payment.setMerOrdId("66721920190626113802");
        payment.setGoodsDesc("xmg");
        System.out.println("pay MerOrdId=" + payment.getMerOrdId());
        payment.setBgRetUrl("https://ats.kingdee.com/auto/weixin/default/business/wsc/hfPosNotify");
//        payment.setMerPiv();
//        payment.setOrdRemark();   41320820190626113043
        hfPayService.payment(payment);
    }

    private static void cancel(HfPayService hfPayService, String orderId) {
        HfCancelDto cancelDto = new HfCancelDto();
        cancelDto.setMemberId(createOrderId());
        cancelDto.setChannelId(HfChannelType.acquire.name());
        cancelDto.setOriVoucherNo(orderId);
        cancelDto.setMerOrdId(createOrderId());
        cancelDto.setMerOperId("cargeer02");
        hfPayService.cancel(cancelDto);
    }

    private static void queryOrder(HfPayService hfPayService, String orderId) {
        HfQueryOrderDto q = new HfQueryOrderDto();
        q.setMemberId("310000016000290258");
        q.setSelfOrdId(orderId);
        q.setIsSale("1");
        hfPayService.queryOrder(q);
    }

    private static void refund(HfPayService hfPayService, String orderId) {
        HfRefundDto q = new HfRefundDto();
        q.setMemberId("310000016000290258");
        q.setOrdAmt("1");
        q.setBgRetUrl("https://ats.kingdee.com/auto/weixin/default/business/wsc/hfPosNotify");
        q.setOriSelfOrdId(orderId);
        q.setMerOrdId(createOrderId());
        System.out.println("refund MerOrdId=" + q.getMerOrdId());     // 35984720190626103415
        hfPayService.refund(q);
    }

    private static String createOrderId() {
        String o = String.valueOf((int) ((Math.random() * 9 + 1) * 100000)).concat(simpleDateFormat.format(new Date()));
        System.out.println(">>>>>>>>>>>" + o);
        return o;
    }

}
