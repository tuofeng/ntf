package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 * 消费
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 11:44
 */
public class HfPaymentDto
        extends HfParameterEncode {
    /**
     * 商户号
     * 必填
     */
    private String memberId;

    /**
     * 支付渠道
     * 必填
     */
    private HfChannelType channelId;

    /**
     * 支付方式,支付渠道为付款码时必填
     */
    private HfMobilePayType mobilePayType;

    /**
     * 订单金额
     * 必填
     */
    private String ordAmt;

    /**
     * 订单号 我们系统生成
     * 必填
     */
    private String merOrdId;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 异步通知地址
     */
    private String bgRetUrl;

    /**
     * 商户私有域
     */
    private String merPiv;

    /**
     * 订单备注
     */
    private String ordRemark;

    /**
     * 自定义第三方打印数据
     */
    private String outPrintData;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public HfChannelType getChannelId() {
        return channelId;
    }

    public void setChannelId(HfChannelType channelId) {
        this.channelId = channelId;
    }

    public HfMobilePayType getMobilePayType() {
        return mobilePayType;
    }

    public void setMobilePayType(HfMobilePayType mobilePayType) {
        this.mobilePayType = mobilePayType;
    }

    public String getOrdAmt() {
        return ordAmt;
    }

    public void setOrdAmt(String ordAmt) {
        this.ordAmt = ordAmt;
    }

    public String getMerOrdId() {
        return merOrdId;
    }

    public void setMerOrdId(String merOrdId) {
        this.merOrdId = merOrdId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getMerPiv() {
        return merPiv;
    }

    public void setMerPiv(String merPiv) {
        this.merPiv = merPiv;
    }

    public String getOrdRemark() {
        return ordRemark;
    }

    public void setOrdRemark(String ordRemark) {
        this.ordRemark = ordRemark;
    }

    public String getOutPrintData() {
        return outPrintData;
    }

    public void setOutPrintData(String outPrintData) {
        this.outPrintData = outPrintData;
    }

    @Override
    protected String getUrl() {
        return "payment";
    }
}
