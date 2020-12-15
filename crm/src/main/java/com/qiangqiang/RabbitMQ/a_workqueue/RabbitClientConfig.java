package com.qiangqiang.RabbitMQ.a_workqueue;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/4
 * \* Time: 14:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class RabbitClientConfig {

    @Test
    public void rabbitClientInit() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.200.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue001",false,false,false,null);
        channel.basicPublish("","queue001",false,false,new AMQP.BasicProperties(),"heheheh111".getBytes());
        channel.close();
        connection.close();
        System.out.println("发送成功");

    }


    @Test
    public void rabbitClientConsumer()throws IOException, TimeoutException{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.200.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue001",false,false,false,null);
        //设置每次只接受一条消息
        channel.basicQos(1);
        int i = 0;
        channel.basicConsume("queue001",false,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String s = new String(body, "utf-8");
                System.out.println(s);
                //签收消息，为手动
                channel.basicAck(envelope.getDeliveryTag(),false);
                //重新放回队列
//                channel.basicNack(envelope.getDeliveryTag(),false,true);
            }
        });
        System.in.read();
    }









}