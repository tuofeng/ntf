package com.kingdee.autopay;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 11:34
 */
public class HfVerifyResult {

    private boolean success = false;
    private int code = -1;
    private JSONObject data = new JSONObject();
    private String rawDate;

    public HfVerifyResult(boolean success, int code, JSONObject data, String rawDate) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.rawDate = rawDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public JSONObject getData() {
        return data;
    }

    public String getRawDate() {
        return rawDate;
    }
}
