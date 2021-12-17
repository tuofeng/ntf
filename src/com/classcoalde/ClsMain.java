package com.classcoalde;

import java.lang.reflect.Method;

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
 * @author: shuangfeng_li 2021/6/25 16:19
 */
public class ClsMain {

    public static void main(String[] args) {
        TestClassLoader classLoader = new TestClassLoader(ClsMain.class.getClassLoader(), "D:\\work");
        try {
            Class c = classLoader.loadClass("com.classcoalde.User");
            Object o = c.newInstance();
            Method m = c.getDeclaredMethod("sout" , null);
            m.invoke(o,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
int a = 1 ;int b = 1;
}
