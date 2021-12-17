package com.classcoalde;

import com.bill.huifu.cfca.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
 * @author: shuangfeng_li 2021/6/25 15:47
 */
public class TestClassLoader
        extends ClassLoader {

    private String path;

    public TestClassLoader(ClassLoader parent, String path) {
        super(parent);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(name.replace("\\.", "/")));
            byte b[] = new byte[in.available()];
            in.read(b);
            in.close();
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

}
