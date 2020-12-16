package com.qiangqiang.Boot.Consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 13:52
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class QueueListener {
    //destination监听的目标
    @JmsListener(destination = "publish.queue",containerFactory = "jmsListenerContainerFactoryQueue")
//    @SendTo("out.queue")
    //sendTo会将此方法返回的数据，写入到queue:out.queue中去
    public void receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
//        return "consumer-a received 返回到out.queue: " + text;
    }
}