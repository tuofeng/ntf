package com;

import java.io.File;
import java.io.FileOutputStream;

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
 * @author: shuangfeng_li 2021/6/17 10:39
 */
public class MMM {

    public static void main(String[] args) {
        saveFile("test.jpg", "sss".getBytes());
    }

    private static String filePath = "D:\\company\\static";


    public static Boolean saveFile(String fileName, byte[] bytes) {
        Boolean flag = false;

//        new File(filePath).mkdirs();
        try {
            File file = new File(filePath, fileName);
            file.mkdirs();
            file.createNewFile();

            file.setWritable(true);

            FileOutputStream out = new FileOutputStream(file);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

}
