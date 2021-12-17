package com.kingdee.autopay;

import com.kingdee.autopay.dto.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 11:32
 */
public interface HfPayService {

    /**
     * ฯ๛ทั
     *
     * @param paymentDto
     * @return
     */
    HfVerifyResult payment(HfPaymentDto paymentDto);

    HfVerifyResult paymentVoid();

    HfVerifyResult refund(HfRefundDto refundDto);

    HfVerifyResult cancel(HfCancelDto cancelDto);

    HfVerifyResult queryOrder(HfQueryOrderDto queryOrderDto);

    HfVerifyResult login(HfLoginDto loginDto);

    HfVerifyResult logout(HfLogOutDto logOutDto);

}