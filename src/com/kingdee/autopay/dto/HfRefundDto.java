package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/13 17:04
 */

public class HfRefundDto
        extends HfParameterEncode {

    /**
     * 商户号
     * 必填
     */
    private String memberId;

    /**
     * 退货金额
     * 必填
     */
    private String ordAmt;

    /**
     * 原三方订单号 对应我们系统的单号
     * 必填
     */
    private String oriSelfOrdId;
    private String oriMerOrdId;

    /**
     * 订单号 退货单号 我们系统生成
     */
    private String merOrdId;

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

    public String getOrdAmt() {
        return ordAmt;
    }

    public void setOrdAmt(String ordAmt) {
        this.ordAmt = ordAmt;
    }

    public String getOriMerOrdId() {
        return oriMerOrdId;
    }

    public void setOriMerOrdId(String oriMerOrdId) {
        this.oriMerOrdId = oriMerOrdId;
    }

    public String getOriSelfOrdId() {
        return oriSelfOrdId;
    }

    public void setOriSelfOrdId(String oriSelfOrdId) {
        this.oriSelfOrdId = oriSelfOrdId;
    }

    public String getMerOrdId() {
        return merOrdId;
    }

    public void setMerOrdId(String merOrdId) {
        this.merOrdId = merOrdId;
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
        return "refund";
    }
}
