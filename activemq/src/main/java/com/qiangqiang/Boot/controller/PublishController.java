package com.qiangqiang.Boot.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;


    @PostMapping(value = "/queue")
    public String queue(HttpServletRequest request ,@RequestBody Book book) throws JMSException {
        System.out.println(book);
        String msg1 = book.getMsg();
        System.out.println(msg1);

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        for (int i = 0; i < 10; i++) {
            textMessage.setText("queue"+i);
            producer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println("chenggong");
                }

                @Override
                public void onException(JMSException exception) {

                }
            });
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
            template.convertAndSend(topic,"topic"+i);
        }
        return "topic发送成功";
    }






}