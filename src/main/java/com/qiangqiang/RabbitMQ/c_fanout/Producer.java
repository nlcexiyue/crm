package com.qiangqiang.RabbitMQ.c_fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/4
 * \* Time: 15:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Producer {

    @Test
    public void sendMessage() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.200.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //设置交换机，设置一个名字叫做logs的交换机，type类型为fanout
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);
        channel.basicPublish("logs","",null,"随便一点".getBytes());
        channel.close();
        connection.close();
        System.out.println("消息发送成功");
    }




}