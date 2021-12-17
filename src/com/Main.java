package com;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2019/9/19 14:10
 */
public class Main {

    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("3");
//        list.add("5");
//        list.add("7");
//        ListIterator lit = list.listIterator();
//        while (lit.hasNext()) {
//            String str = (String) lit.next();
//            if (str.equals("3")) {
//                lit.add("12");
//            }
//            System.out.println(str);
//        }
//        System.out.println(list.size());

//        String s1 = "hello";
//        String s2 = "hello";
//        System.out.println(s1==s2);
//
//        String s1 = new String("hello"); //同时在字符串常量池和堆中创建hello，并返回堆中hello的引用
//        String s2 = s1.intern(); // 指向字符串常量池中的hello
//        System.out.println(s1 == s2);   //false  s2先去字符串常量池找hello，由于存在所以直接返回字符串常量池中hello的引用
//
//        String s0 = "hello";
//        String s1 = new String("hello");
//        String s2 = s1.intern();
//        System.out.println(s0==s2);   //true  s2先去字符串常量池找hello，由于存在所以直接返回字符串常量池中hello的引用

        File f = new File("D:\\a");
        f.setWritable(true);
        byte[] b = "ssss".getBytes();
        try {
            FileOutputStream o = new FileOutputStream(f);
            o.write(b);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private static List<Map> mergeResult(List<Map> mainList, List<Map> addList) {
        List<Map> retList = new ArrayList<>();

        retList.addAll(mainList);
        retList.addAll(addList);
        Map<String, Integer> retMap = new HashMap();
        for (Map map : retList) {
            String companyid = (String) map.get("EXTEND_APP_COMPANY");
            int count = (int) map.get("DCOUNT");
            if (retMap.containsKey(companyid)) {
                count = count + retMap.get(companyid);
            }
            retMap.put(companyid, count);
        }
        retList.clear();

        Set<Map.Entry<String, Integer>> entries = retMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            Map m = new HashMap();
            m.put("EXTEND_APP_COMPANY", entry.getKey());
            m.put("DCOUNT", entry.getValue());
            retList.add(m);
        }
        return retList;
    }

    public static Map<String, Object> toMap(Object... values) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i += 2) {
            map.put(String.valueOf(values[i]), values[i + 1]);
        }
        return map;
    }
}
