package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/13 17:04
 */

public class HfCancelDto
        extends HfParameterEncode {

    /**
     * �̻���
     * ����
     */
    private String memberId;

    /**
     * �˻����
     * ����
     */
    private String channelId;

    /**
     * ԭ���������� ��Ӧ����ϵͳ�ĵ���
     * ����
     */
    private String mobilePayType;

    /**
     * ������ �˻����� ����ϵͳ����
     */
    private String oriVoucherNo;

    /**
     * �첽֪ͨ��ַ
     */
    private String merOrdId;

    private String merOperId;

    public String getMerOperId() {
        return merOperId;
    }

    public void setMerOperId(String merOperId) {
        this.merOperId = merOperId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMobilePayType() {
        return mobilePayType;
    }

    public void setMobilePayType(String mobilePayType) {
        this.mobilePayType = mobilePayType;
    }

    public String getOriVoucherNo() {
        return oriVoucherNo;
    }

    public void setOriVoucherNo(String oriVoucherNo) {
        this.oriVoucherNo = oriVoucherNo;
    }

    public String getMerOrdId() {
        return merOrdId;
    }

    public void setMerOrdId(String merOrdId) {
        this.merOrdId = merOrdId;
    }

    @Override
    protected String getUrl() {
        return "paymentVoid";
    }
}
