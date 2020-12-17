package com.qiangqiang.Boot.Consumer;

import com.qiangqiang.service.NewsLibiaryService;
import com.qiangqiang.tool.SnowFlakeId;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.qiangqiang.Boot.controller.PublishController.division;

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
    @Reference(timeout = 60000,version = "1.0.0")
    private NewsLibiaryService newsLibiaryService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 监听新闻队列中的消息
     *
     */
    @JmsListener(destination = "news.queue",containerFactory = "jmsListenerContainerFactoryQueue")
    @SendTo("out1.queue")
    public void insertNewsLibrary(String text){
        String[] split = text.split(division);
        long id = SnowFlakeId.generateId();
        LocalDateTime parse = LocalDateTime.parse(split[2], DATE_TIME_FORMATTER);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = parse.atZone(zone).toInstant();
        Date date = Date.from(instant);
        int insert = newsLibiaryService.insert(id, split[0], split[1], date);
        System.out.println("");
    }




    //destination监听的目标
    @JmsListener(destination = "publish.queue",containerFactory = "jmsListenerContainerFactoryQueue")
    @SendTo("out.queue")
    //sendTo会将此方法返回的数据，写入到queue:out.queue中去
    public void receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);

    }


}