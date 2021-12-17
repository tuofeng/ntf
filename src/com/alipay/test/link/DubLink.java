package com.alipay.test.link;

import org.apache.commons.lang.text.StrBuilder;

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
 * @author: shuangfeng_li 2021/11/1 11:06
 */
public class DubLink<T> {

    private DubNode<T> head;
    private DubNode<T> tail;

    public void insertHead(T entry) {
        DubNode newNode = new DubNode(entry);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.pre = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    public void del(int position) {
        if (position == 0) {
            head = null;
            tail = null;
        } else {
            DubNode temp = head;
            for (int i = 1; i < position; i++) {
                temp = temp.next;
            }
            if (temp != null && temp.next != null) {
                temp.next = temp.next.next;
            }
            if (temp != null && temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return null;
        }
        StrBuilder s = new StrBuilder();
        DubNode node = head;
        while (node != null) {
            s.append(node.getEntry().toString());
            node = node.next;
        }
        return s.toString();
    }
}
