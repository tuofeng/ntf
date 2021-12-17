package com.alipay.test;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
 * @author: shuangfeng_li 2021/10/26 9:41
 */
public class Main {

    public static void main(String[] args) {
//        Account a = new Account();
//        Account b = new Account();
//        a.transfer(b, 100);
//        b.transfer(a, 200);

        JSONObject root = new JSONObject();

        JSONObject owner_setting = new JSONObject();
        owner_setting.put("contact_email" , "frank_yz@kingdee.com");
        owner_setting.put("notice_method" , "公告");

        JSONArray setting_list = new JSONArray();
        //商城
//        setting_list.put(toJson("UserInfo" , "在互动活动、商品评价等场景中展示您的个性化形象"));
//        setting_list.put(toJson("Location" , "基于位置为您匹配就近的门店和服务"));
//        setting_list.put(toJson("PhoneNumber" , "快速进行注册与车主认证"));
//        setting_list.put(toJson("AlbumWriteOnly","将顾问的电子名片以图片形式存储至您的相册"));
//        setting_list.put(toJson("Album","在投票活动中上传报名信息"));
//        setting_list.put(toJson("Contact","存储顾问联系方式"));
//        setting_list.put(toJson("MessageFile","在投票活动中上传报名信息"));


        //企微小程序
        setting_list.put(toJson("Camera" , "维护您的电子名片个人形象"));
        setting_list.put(toJson("Album" , "维护您的电子名片个人形象"));
        setting_list.put(toJson("AlbumWriteOnly" , "将您的电子名片以图片形式存储至您的相册"));
        setting_list.put(toJson("Location" , "基于位置为您匹配就近的门店和服务"));

        //江宁小程序
//        setting_list.put(toJson("UserInfo" , "在互动活动、商品评价等场景中展示您的个性化形象"));
//        setting_list.put(toJson("Location" , "基于位置为您匹配就近的门店和服务"));
//        setting_list.put(toJson("PhoneNumber" , "快速进行注册与车主认证"));
//        setting_list.put(toJson("Album" , "在投票活动中上传报名信息"));
//        setting_list.put(toJson("MessageFile" , "在投票活动中上传报名信息"));
//        setting_list.put(toJson("AlbumWriteOnly" , "将顾问的电子名片以图片形式存储至您的相册"));

        root.put("owner_setting" , owner_setting);
        root.put("setting_list" , setting_list);
        System.out.println(root.toString());
    }

    public static JSONObject toJson(String privacy_key , String privacy_text){
        JSONObject e1 = new JSONObject();
        e1.put("privacy_key", privacy_key);
        e1.put("privacy_text", privacy_text);
        return e1;
    }

    static class Account {
        private Allocator actr = Main.getInstance();
        private int balance;

        void transfer(Account target, int amt) {
            while (!actr.apply(this, target)) {
            }
            try {
                synchronized (this) {
                    System.out.println(this.toString() + " lock lock1");
                    synchronized (target) {
                        System.out.println(this.toString() + " lock lock2");
                        if (this.balance > amt) {
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            } finally {
                actr.clean(this, target);
            }
        }
    }

    static class Allocator {
        private List<Account> als = new ArrayList<>();

        private void Allocator() {
        }

        synchronized boolean apply(Account from, Account to) {
            if (als.contains(from) || als.contains(to)) {
                return false;
            } else {
                als.add(from);
                als.add(to);
            }
            return true;
        }

        synchronized void clean(Account from, Account to) {
            als.remove(from);
            als.remove(to);
        }
    }

    private static class SingleTonHoler {
        private static Allocator INSTANCE = new Allocator();
    }

    public static Allocator getInstance() {
        return SingleTonHoler.INSTANCE;
    }


}
