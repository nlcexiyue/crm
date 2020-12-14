package com.qiangqiang.RabbitMQ;

import org.junit.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/10
 * \* Time: 10:11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Rabbit_Confirm {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void messageSendTestWithConfirm(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(!ack){

                    System.out.println("correlationData："+ correlationData);
                    System.out.println("ack："+ack);
                    System.out.println("cause："+cause);
                }
            }
        });
        //消息内容
        Map<String,String> map = new HashMap<>();
        map.put("message","testing confire function");
        String uuid = UUID.randomUUID().toString();
        //设置消息自定义id
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uuid);
        rabbitTemplate.convertAndSend("exchange-dog","dog",map,correlationData);


    }


}