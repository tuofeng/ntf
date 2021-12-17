package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 * ����
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 11:44
 */
public class HfPaymentDto
        extends HfParameterEncode {
    /**
     * �̻���
     * ����
     */
    private String memberId;

    /**
     * ֧������
     * ����
     */
    private HfChannelType channelId;

    /**
     * ֧����ʽ,֧������Ϊ������ʱ����
     */
    private HfMobilePayType mobilePayType;

    /**
     * �������
     * ����
     */
    private String ordAmt;

    /**
     * ������ ����ϵͳ����
     * ����
     */
    private String merOrdId;

    /**
     * ��Ʒ����
     */
    private String goodsDesc;

    /**
     * �첽֪ͨ��ַ
     */
    private String bgRetUrl;

    /**
     * �̻�˽����
     */
    private String merPiv;

    /**
     * ������ע
     */
    private String ordRemark;

    /**
     * �Զ����������ӡ����
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
