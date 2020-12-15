package com.qiangqiang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;
    @Test
    void contextLoads() {
//        for (int i = 0; i < 10; i++) {
//            jms.convertAndSend(queue,"queue"+i);
//        }
        for (int i = 0; i < 10; i++) {
            jms.convertAndSend(topic,"topic"+i);
        }
    }

}
