package com.oppo;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * 升级规则类型
 *
 * @author: shuangfeng_li 2020/10/16 10:42
 */
public enum UpgradeRuleTypeEnum {

    TOTAL_CONSUME_AMOUNT("TOTAL_CONSUME_AMOUNT", "累计消费金额", true),
    TOTAL_CONSUME_COUNT("TOTAL_CONSUME_COUNT", "累计消费次数", true),
    SINGLE_CONSUME_AMOUNT("SINGLE_CONSUME_AMOUNT", "单次消费金额", true),
    TOTAL_RECOMMEND_FANS("TOTAL_RECOMMEND_FANS", "累计推荐粉丝人数", false),
    TOTAL_RECOMMEND_CUST("TOTAL_RECOMMEND_CUST", "累计推荐车主认证人数", false),
    TOTAL_RECOMMEND_REPAIR("TOTAL_RECOMMEND_REPAIR", "维修进场次数", true);
    private String key;
    private String context;
    private boolean valid;

    public String getKey() {
        return key;
    }

    public String getContext() {
        return context;
    }

    public boolean isValid() {
        return valid;
    }

    UpgradeRuleTypeEnum(String key, String context, boolean valid) {
        this.key = key;
        this.context = context;
        this.valid = valid;
    }



}
