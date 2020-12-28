package com.qiangqiang;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/28
 * \* Time: 17:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ArrayList {
    /**
     * 定义一个数组,用于保存集合中的数据
     */
    private Object[] elementData;

    /**
     * 定义一个变量,用于保存集合中实际存放元素的个数
     */
    private int size;

    /**
     * 方法:获取元素中实际存放的元素
     */
    private int size(){
        return this.size;
    }

    /**
     * 构造方法()设置elementData数组的空间长度默认为10
     */
    public ArrayList(){
        this.elementData = new Object[10];
    }

    /**
     * 方法:指定设置elementData数组的空间长度
     */
    public ArrayList(int cap){
        //判断cap变量是否合法
        if(cap < 0){
            throw new RuntimeException("参数不合法" + cap);
        }
        //实例化elementData数组
        this.elementData = new Object[cap];
    }

    /**
     * 方法 : add 方法
     * @param element
     */
    public void add(Object element){
        //判断数组是否需要扩容
        //当数组的空间长度等于数组实际存放的元素的个数时,需要扩容操作
        if(elementData.length == size){
            //创建一个比原数组更大的数组
            Object[] newArr = new Object[elementData.length * 2 + 1];
            //将原数组中的元素放到新数组中
            for (int i = 0; i < size ; i++) {
                newArr[i] = elementData[i];
            }
            //让原数组保存新数组的地址值
            elementData = newArr;
        }
        //把element放入数组中
        elementData[size] = element;
        //更新size的值
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        System.out.println(arrayList);



    }








}