package com.kingdee.autopay;

import com.kingdee.autopay.dto.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 14:12
 */
public class HfPayServiceImpl
        implements HfPayService {

    HfPay hfPay = new HfPay();

    @Override
    public HfVerifyResult payment(HfPaymentDto paymentDto) {
        return hfPay.doBusiness(paymentDto.encodeParameter());
    }

    @Override
    public HfVerifyResult paymentVoid() {
        return null;
    }

    @Override
    public HfVerifyResult refund(HfRefundDto refundDto) {
        return hfPay.doBusiness(refundDto.encodeParameter());
    }

    @Override
    public HfVerifyResult cancel(HfCancelDto cancelDto) {
        return hfPay.doBusiness(cancelDto.encodeParameter());
    }

    @Override
    public HfVerifyResult queryOrder(HfQueryOrderDto queryOrderDto) {
        return hfPay.doBusiness(queryOrderDto.encodeParameter());
    }

    @Override
    public HfVerifyResult login(HfLoginDto loginDto) {
        return hfPay.doBusiness(loginDto.encodeParameter());
    }

    @Override
    public HfVerifyResult logout(HfLogOutDto logOutDto) {
        return hfPay.doBusiness(logOutDto.encodeParameter());
    }


}
