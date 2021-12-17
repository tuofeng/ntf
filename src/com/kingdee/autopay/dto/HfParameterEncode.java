package com.kingdee.autopay.dto;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 15:24
 */
public abstract class HfParameterEncode {

    protected abstract String getUrl();

    public String encodeParameter() {
        StringBuilder pm = new StringBuilder().append("payment://com.pnr.pospp/").append(getUrl());
        Field[] fields = this.getClass().getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            Object obj;
            try {
                field.setAccessible(true);
                obj = field.get(this);
                if (obj != null && !Modifier.isStatic(field.getModifiers())) {
                    String value = getString(obj);
                    if (value == null || value.length() == 0) {
                        value = "";
                    }
                    pm.append(first ? "?" : "&").append(field.getName()).append("=").append(value);
                    first = false;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return pm.toString();
    }


    public String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof Date) {
            return formatDate((Date) obj, "yyyy-MM-dd HH:mm:ss");
        } else if (obj instanceof byte[]) {
            return new String((byte[]) obj);
        } else if (isSimpleType(obj.getClass())) {
            return String.valueOf(obj);
        } else {
            return obj.toString();
        }
    }

    public static String formatDate(Date d, String format) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(d);
    }

    public static boolean isSimpleType(Class<?> t) {
        return t == Boolean.class
                || t == Double.class
                || t == String.class
                || t == Character.class
                || t == Integer.class
                || t == Float.class
                || t == Byte.class
                || t == Short.class
                || t == Long.class
                || t == boolean.class
                || t == double.class
                || t == char.class
                || t == int.class
                || t == float.class
                || t == byte.class
                || t == short.class
                || t == long.class
                || t == Date.class;
    }

}
