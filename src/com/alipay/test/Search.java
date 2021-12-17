package com.alipay.test;

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
 * @author: shuangfeng_li 2021/10/27 9:50
 */
public class Search {

    public int doSearch(int arr[], int searchValue, int start, int end) {
        if (searchValue < arr[0] || searchValue > arr[arr.length - 1]) {
            return -1;
        }
        int middle = (end - start) / 2 + start;
        if (arr[middle] == searchValue) {
            return middle;
        } else if (searchValue > arr[middle]) {
            return doSearch(arr, searchValue, middle + 1, end);
        } else if (searchValue < arr[middle]) {
            return doSearch(arr, searchValue, start, middle - 1);
        }
        return -1;
    }

}
