package com.qiangqiang.RabbitMQ.c_fanout;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/4
 * \* Time: 15:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Consumer2 {

    @Test
    public void consumerMessage() throws IOException, TimeoutException {
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
        //从通道里面得到一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //把临时队列和交换机进行绑定
        channel.queueBind(queue,"logs","");
        //再次接受消息
        channel.basicConsume(queue,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                System.out.println("2号消费者："+ new String(body,"utf-8"));

            }
        });
        System.in.read();
    }


}