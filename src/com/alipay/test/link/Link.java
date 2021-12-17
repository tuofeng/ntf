package com.alipay.test.link;

import org.apache.commons.lang.text.StrBuilder;

import java.util.LinkedList;

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
 * @author: shuangfeng_li 2021/11/1 10:06
 */
public class Link<T> {

    private Node head;

    public void add(T entry) {
        Node newNode = new Node(entry);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void add(int position, T entry) {
        Node newNode = new Node(entry);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 1; i < position; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public void del(int position) {
        if (position == 0) {
            if (head != null) {
                head = head.next;
            }
        } else {
            Node temp = head;
            for (int i = 1; i < position; i++) {
                temp = temp.next;
            }
            if (temp != null && temp.next != null) {
                temp.next = temp.next.next;
            }
        }
    }

    public Node find() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("aa");
        linkedList.add("bbb");
        linkedList.isEmpty();
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return null;
        }
        StrBuilder s = new StrBuilder();
        Node node = head;
        while (node != null) {
            s.append(node.getEntry().toString());
            node = node.next;
        }
        return s.toString();
    }
}
