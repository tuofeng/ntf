package com.oppo;

import com.google.gson.JsonObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.groovy.GJson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

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
 * @author: shuangfeng_li 2021/4/6 17:25
 */
public class JsMain {

    public static void main(String[] args) throws Exception {

//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.MONTH, 3);
//        c.set(Calendar.DAY_OF_MONTH, 3);
//        c.set(Calendar.HOUR_OF_DAY, 9);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);



        String pattern ="\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";
        //  应收日期 2019/1/1
        // 2019-01-02
        // 2019.02.02
        // 20190909

        boolean isMatchDate1 = Pattern.matches(pattern, "2019-01-02");
        boolean isMatchDate2 = Pattern.matches(pattern, "2019/1/1");
        boolean isMatchDate3 = Pattern.matches(pattern, "2019.02.02");
        boolean isMatchDate4 = Pattern.matches(pattern, "20190909");
        System.out.println("字符串中是否是日期' 子字符串? " + isMatchDate1+";"+isMatchDate2+";"+isMatchDate3+";"+isMatchDate4+";");



        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-01 11:11:11");

        if (endDate != null) {
            SimpleDateFormat daySdf = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDay = daySdf.parse(daySdf.format(Calendar.getInstance().getTime()));
            endDate = daySdf.parse(daySdf.format(endDate));
            if (endDate.after(nowDay)) {


            } else {
                SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
                SimpleDateFormat mdSdf = new SimpleDateFormat("MM-dd");
                endDate = daySdf.parse(yearSdf.format(nowDay).concat("-").concat(mdSdf.format(endDate)));
                if (endDate.before(nowDay)) {
                    String year = String.valueOf(Integer.parseInt(yearSdf.format(nowDay)) + 1);
                    endDate = daySdf.parse(year.concat("-").concat(mdSdf.format(endDate)));
                }
            }
        }

        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate));

    }


}
