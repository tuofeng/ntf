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
public class FixOpp {

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
                if (line.contains("t_sal_opportunity")) {
                    complete = false;
                }
                if (line.contains("@33") && entryProcessList.contains("### SET")) {
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
        if (entryList.size() != 68) {
            return null;
        }
        String id = entryList.get(2);
        String personId = entryList.get(9);
        String allocateTime = entryList.get(10);
        String allocateOrgTime = entryList.get(11);
        String orgId = entryList.get(17);
        if (StringUtils.isBlank(id) || !id.contains("###   @1=")) {
            return null;
        }
        if (StringUtils.isBlank(personId) || !personId.contains("###   @8=")) {
            return null;
        }
        if (StringUtils.isBlank(allocateTime) || !allocateTime.contains("###   @9=")) {
            return null;
        }
        if (StringUtils.isBlank(allocateOrgTime) || !allocateOrgTime.contains("###   @10=")) {
            return null;
        }
        if (StringUtils.isBlank(orgId) || !orgId.contains("###   @16=")) {
            return null;
        }
        id = id.substring(id.indexOf("###   @1=") + 9);
        personId = personId.substring(personId.indexOf("###   @8=") + 9);
        allocateTime = allocateTime.substring(allocateTime.indexOf("###   @9=") + 9);
        allocateOrgTime = allocateOrgTime.substring(allocateOrgTime.indexOf("###   @10=") + 10);
        orgId = orgId.substring(orgId.indexOf("###   @16=") + 10);

        if (StringUtils.isBlank(id)) {
            return null;
        }
        return new Opp(id, personId, orgId, allocateTime, allocateOrgTime);
    }

    static class Opp {
        private String id;
        private String personId;
        private String orgId;
        private String allocateTime;
        private String allocateOrgTime;

        public Opp(String id, String personId, String orgId, String allocateTime, String allocateOrgTime) {
            this.id = id;
            this.personId = personId;
            this.orgId = orgId;
            this.allocateTime = allocateTime;
            this.allocateOrgTime = allocateOrgTime;
        }

        public String toUpdateSql() {
            if (StringUtils.isBlank(id)) {
                return null;
            }
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE `t_sal_opportunity` set ");
            sql.append(" FPersonID=").append(personId);
            sql.append(" ,FAllocateTime=").append(allocateTime);
            sql.append(" ,FAllocateOrgTime=").append(allocateOrgTime);
            sql.append(" ,FORGUNITID=").append(orgId);
            sql.append(" where FID=").append(id).append(";");

            return sql.toString();
        }

    }

    public static void writ(List<String> sqls) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("d://sql.txt");
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
