package com;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @Date: 2020/5/9 14:23
 */
public class FixAdmin {

    public static void main(String[] args) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        List<Opp> opps = new ArrayList<>();
        try {
            reader = new FileReader("d://2.log");
            bufferedReader = new BufferedReader(reader);
            String line = null;
            List<String> entryProcessList = new ArrayList<>();
            boolean complete = false;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                line = line.trim();
                if (line.contains("t_wx_org_admin")) {
                    complete = false;
                }
                if (line.contains("@49") && entryProcessList.contains("### SET")) {
                    complete = true;
                }
                if (complete) {
                    Opp opp = toOpp(entryProcessList);
                    if (opp != null) {
                        opps.add(opp);
                    }
                    entryProcessList.clear();
                } else {
                    entryProcessList.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                reader.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        List<String> sqls = new ArrayList<>();
        for (Opp opp : opps) {
            String sql = opp.toUpdateSql();
            if (StringUtils.isNotBlank(sql)){
                sqls.add(sql);
            }
            System.out.println(sql);
        }
        writ(sqls);

    }


    public static Opp toOpp(List<String> entryList) {
        if (entryList.size() != 100) {
            return null;
        }
        String id = entryList.get(2);
        String name = entryList.get(4);
        if (StringUtils.isBlank(id) || !id.contains("###   @1=")) {
            return null;
        }
        id = id.substring(id.indexOf("###   @1=") + 9);
        name = name.substring(name.indexOf("###   @3=") + 9);

        if (StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
            return null;
        }
        return new Opp(id, name);
    }

    static class Opp {
        private String id;
        private String name;

        public Opp(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toUpdateSql() {
            if (StringUtils.isBlank(id)) {
                return null;
            }
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `t_wx_org_admin` set ");
            sql.append(" FNAME=").append(name);
            sql.append(" where FID=").append(id).append(";");

            return sql.toString();
        }

    }

    public static void writ(List<String> sqls) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("d://admin.txt");
            bw = new BufferedWriter(fw);

            for (String arr : sqls) {
                bw.write(arr + "\t\n");
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
