package com;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/3/18 16:19
 */
public class DesUtil {

    private Key mKey;
    private Cipher mDecryptCipher;
    private Cipher mEncryptCipher;

    public DesUtil(String key) throws Exception {
        initKey(key);
        initCipher();
    }

    public void initKey(String keyRule) {
        byte[] keyByte = keyRule.getBytes();
        byte[] byteTemp = new byte[8];
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        mKey = new SecretKeySpec(byteTemp, "DES");
    }

    private void initCipher() throws Exception {
        mEncryptCipher = Cipher.getInstance("DES");
        mEncryptCipher.init(Cipher.ENCRYPT_MODE, mKey);

        mDecryptCipher = Cipher.getInstance("DES");
        mDecryptCipher.init(Cipher.DECRYPT_MODE, mKey);
    }

    public void doEncryptFile(String filePath, String savePath) {
        FileInputStream in = null;
        CipherInputStream cin = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(new File(filePath));
            cin = new CipherInputStream(in, mEncryptCipher);
            os = new FileOutputStream(new File(savePath));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = cin.read(bytes)) > 0) {
                os.write(bytes, 0, len);
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                cin.close();
                in.close();
            } catch (Exception e) {

            }
        }
    }


    public void doDecryptFile(String filePath, String savePath) {
        FileInputStream in = null;
        CipherInputStream cin = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(new File(filePath));
            cin = new CipherInputStream(in, mDecryptCipher);
            os = new FileOutputStream(savePath);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = cin.read(bytes)) > 0) {
                os.write(bytes, 0, len);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                cin.close();
                in.close();
            } catch (Exception e) {

            }
        }
    }


    public static void main(String[] args) throws Exception {

//        DesUtil desUtil = new DesUtil("executesystem99");
////        desUtil.doEncryptFile("d:/7", "d:/7_temp");
//        desUtil.doDecryptFile("C:\\Users\\Administrator\\Desktop\\asd\\11temp_sql", "C:\\Users\\Administrator\\Desktop\\asd\\11temp_sql_2");
//

//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DAY_OF_YEAR, -1 * 1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));

//        String s = "abc";
//        System.out.println();
        do{
            System.out.println("fsdfsfs");
        }while (false);
    }

    public static void a() {

        String args = "www.dd.ww.zz";
        Pattern p = Pattern.compile("[^.]+[.][^.]+[.][^.]+");
        Matcher m = p.matcher(args);
        while (m.find()){
            System.out.print(m.group());
        }


    }



    public static String checkMobileNumber(String mobileNumber){

        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(mobileNumber);
        String dynamicPassword = "";
        while (m.find()) {
            String s = m.group();
            if (m.group().length() == 11) {
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }

    public static void getWeek(int index) throws Exception{

        ArrayList<Date> list = new ArrayList<Date>();
        long time = new Date().getTime() - 24*60*60*1000;
        Date date = new Date(time);
        int weekIndex = (date.getDay()==0)? 7:date.getDay();
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1*(weekIndex-1)+7*index);
        list.add(calendar.getTime());
        calendar.add(calendar.DATE,6 );
        list.add(calendar.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print(new Date().getTime());

    }

    public static int getQuot(Date from ,Date to){
        int s = 0;
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            s = (int) ((to.getTime() - from.getTime()) / (1000 * 60 * 60 * 24));
        }   catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.print(s);
            return s;
        }
    }

    public static List<Date> dateToWeek(int index) {

        List<Date> list = new ArrayList<Date>();
        Date date = new Date();
        int weekIndex = date.getDay();
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1*(weekIndex-1)+7*index);
        list.add(calendar.getTime());
        calendar.add(calendar.DATE,6 );
        list.add(calendar.getTime());
        return list;
    }

    public static File chooseUniqueFilename(File mp3File) throws Exception {
        File file = mp3File;
        try {
            if (!mp3File.exists()) {
                file = mp3File;
            } else {
                String filePath = mp3File.getAbsolutePath();
                String filename = "";
                String extension = "";
                if (filePath.indexOf(".") < 0) {
                    filename = filePath;
                } else {
                    filename = filePath.substring(0, filePath.lastIndexOf("."));
                    extension = filePath.substring(filePath.lastIndexOf("."), filePath.length());
                }

                String fullFilename = filename + extension;
                filename = filename + "_";
                int sequence = 1;
                for (int magnitude = 1; magnitude < 1000000000; magnitude *= 10) {
                    for (int iteration = 0; iteration < 9; ++iteration) {
                        fullFilename = filename + sequence + extension;
                        if (!new File(fullFilename).exists()) {
                            file = new File(fullFilename);
                            return file;
                        }
                        sequence += new Random().nextInt(magnitude) + 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return file;
        }

    }

}