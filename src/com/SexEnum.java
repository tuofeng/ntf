package com;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/3/12 9:06
 */
public enum SexEnum {

    T_ORDER("DCD_ORDER", "订单线索(卖车通)"),
    T_400("DCD_400", "400线索(卖车通)"),
    T_PUBLIC("DCD_PUBLIC", "公池线索(卖车通)"),
    T_ACTIVITY("DCD_ACTIVITY", "活动线索(卖车通)"),
    T_DRIVE("DCD_DRIVE", "试驾线索(卖车通)"),
    T_INCREMENT("DCD_INCREMENT", "增量线索(卖车通)"),
    T_IM("DCD_IM", "IM线索(卖车通)"),
    T_LIVE("DCD_LIVE", "直播线索(卖车通)");

    private String key;
    private String context;

    public String getKey() {
        return this.key;
    }

    public String getContext() {
        return this.context;
    }

    private SexEnum(String key, String context) {
        this.key = key;
        this.context = context;
    }



}
