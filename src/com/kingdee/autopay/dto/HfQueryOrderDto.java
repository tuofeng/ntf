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
     * �̻���
     */
    private String memberId;

    /**
     * �߼��ն˺�
     */
    private String pnrDevId;

    /**
     * ������������
     */
    private String selfOrdId;

    /**
     * �Ƿ����ѽ���  0-��(��������)�� 1 ��������-�ǣ��������ף�
     * ����
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
