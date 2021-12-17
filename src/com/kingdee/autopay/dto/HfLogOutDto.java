package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 19:07
 */
public class HfLogOutDto
        extends HfParameterEncode {

    @Override
    protected String getUrl() {
        return "logout";
    }
}
