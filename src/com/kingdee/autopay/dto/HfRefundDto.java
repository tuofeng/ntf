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
     * �̻���
     * ����
     */
    private String memberId;

    /**
     * �˻����
     * ����
     */
    private String ordAmt;

    /**
     * ԭ���������� ��Ӧ����ϵͳ�ĵ���
     * ����
     */
    private String oriSelfOrdId;
    private String oriMerOrdId;

    /**
     * ������ �˻����� ����ϵͳ����
     */
    private String merOrdId;

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
