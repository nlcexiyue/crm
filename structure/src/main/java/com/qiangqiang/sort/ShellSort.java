package com.qiangqiang.sort;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/31
 * \* Time: 17:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ShellSort {

    //希尔排序
    public static void main(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(100);
        }
        int length = arr.length;
        //算法交换元素的执行总数
        int a = 0;

        //step步长
        for (int step = length / 2; step > 0; step /= 2) {
            for (int i = 1; i < length ; i ++) {

                for (int j = i; j <length ; j += step) {
                    if(arr[i] > arr[j]){
                        int temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                    a++;
                }

            }
        }


        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(a);

    }
}