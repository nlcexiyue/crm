package com.qiangqiang.Boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 13:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/publish")
public class PublishController {
    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;


    @RequestMapping("/queue")

    public String queue(){
        JmsTemplate jmsTemplate = jms.getJmsTemplate();
        for (int i = 0; i < 10; i++) {
            jms.convertAndSend(queue,"queue"+i);
        }
        return "queue发送成功";
    }

    @JmsListener(destination = "out.queue")
    public void comsumerMsg(String msg){
        System.out.println(msg);
    }

    @RequestMapping("/topic")
    public String topic(){

        for (int i = 0; i < 10; i++) {
            jms.convertAndSend(topic,"topic"+i);
        }
        return "topic发送成功";
    }






}