package com;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2018/7/13 9:50
 */
public class SmsXmlUtils {
    private static SAXBuilder saxBuilder;
    private static Object syncObject = new Object();

    static {
        saxBuilder = new SAXBuilder();
        saxBuilder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", false);
        saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        saxBuilder.setExpandEntities(false);
        saxBuilder.setValidation(false);
    }

    public static void main(String[] args) {
        String s = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                " <!DOCTYPE foo [  \n" +
                " <!ELEMENT foo ANY >\n" +
                " <!ENTITY xxe SYSTEM \"file:///etc/passwd\" >]><foo>&xxe;</foo>";
        Map m = xmltoMap(s);
        m.toString();
    }

    public static Map xmltoMap(String xml) {
        if (xml == null || "".equals(xml)) {
            return null;
        }
        ByteArrayInputStream bin = null;
        try {
            bin = new ByteArrayInputStream(xml.getBytes("utf-8"));
            Document doc = null;
            synchronized (syncObject) {
                doc = saxBuilder.build(bin);
            }
            Element root = doc.getRootElement();
            return element2Map(root);
        } catch (Exception e) {
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    private static Map element2Map(Element element) {
        Map msgMap = new HashMap();
        List<Element> messList = element.getChildren();
        Iterator it = messList.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            List<Element> chs = e.getChildren();
            if (chs.size() == 0) {
                msgMap.put(e.getName().toLowerCase(), e.getValue());
            } else {
                Map map = element2Map(e);
                Object o = msgMap.get(e.getName().toLowerCase());
                if (o == null) {
                    msgMap.put(e.getName().toLowerCase(), map);
                } else {
                    if (o instanceof List) {
                        ((List) o).add(map);
                    } else if (o instanceof Map) {
                        List list = new ArrayList();
                        list.add(map);
                        list.add(o);
                        msgMap.put(e.getName().toLowerCase(), list);
                    }
                }
            }
        }
        return msgMap;
    }


}
