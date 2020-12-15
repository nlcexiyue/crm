package com.qiangqiang;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/7
 * \* Time: 15:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ssss {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("A");
        list.add("BC");
        list1.add("AB");
        list1.add("C");
        System.out.println(checkDiffrent4(list, list1));
    }

    /**
     * 使用stream流去比较两个数组是否相等
     * 方法5
     */
    private static boolean checkDiffrent4(List<String> list, List<String> list1) {
        long st = System.nanoTime();
        System.out.println("消耗时间为： " + (System.nanoTime() - st));
        /** 先将集合转成stream流进行排序然后转成字符串进行比较 */
        return list.stream().sorted().collect(Collectors.joining())
                .equals(list1.stream().sorted().collect(Collectors.joining()));
    }
}