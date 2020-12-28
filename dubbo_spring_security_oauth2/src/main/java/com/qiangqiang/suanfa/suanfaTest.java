package com.qiangqiang.suanfa;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.NaN;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/28
 * \* Time: 16:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class suanfaTest {
    public static void main(String[] args) {
//        int [] arr = {1,2,3,4};
//        System.out.println(arr.length);
//        float v = 1.0f / 0.0f;
//        float a = NaN;
//        System.out.println(a);
        List<Object> list = new ArrayList<>();
//
//        System.out.println(v);
//
//        System.out.println(a);
        int s = 1;
        Integer integer = null;
        try {
            integer = Integer.valueOf("1.1");
        } catch (NumberFormatException e) {
            System.out.println("不是");
        }
        System.out.println(integer);


    }
}