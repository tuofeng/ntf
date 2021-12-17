package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/13 14:09
 */
public class HfQueryOrderDto
        extends HfParameterEncode {
    /**
     * 商户号
     */
    private String memberId;

    /**
     * 逻辑终端号
     */
    private String pnrDevId;

    /**
     * 第三方订单号
     */
    private String selfOrdId;

    /**
     * 是否消费交易  0-否(即反向交易)； 1 或者其他-是（即正向交易）
     * 必填
     */
    private String isSale;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPnrDevId() {
        return pnrDevId;
    }

    public void setPnrDevId(String pnrDevId) {
        this.pnrDevId = pnrDevId;
    }

    public String getSelfOrdId() {
        return selfOrdId;
    }

    public void setSelfOrdId(String selfOrdId) {
        this.selfOrdId = selfOrdId;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    @Override
    protected String getUrl() {
        return "queryOrder";
    }
}
