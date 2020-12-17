package com.qiangqiang.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 14:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ZookeeperTest {
    private static final String zkServers = "192.168.200.111:2181";
    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient(zkServers,1000,10000);
        zkClient.writeData("/sanguo" , "1118");

        System.out.println("操作成功");
    }
}