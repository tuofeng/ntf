package com.alipay.test.link;

import java.util.List;

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
 *
 * @author: shuangfeng_li 2021/11/1 10:13
 */
public class LinkMain {

    public static void main(String[] args) {
        DubLink<String> link = new DubLink<>();

        link.insertHead("3");
        link.insertHead("2");
        link.insertHead("1");

        link.del(1);
        link.del(1);
        link.del(0);

        System.out.println(link.toString());
    }

}
