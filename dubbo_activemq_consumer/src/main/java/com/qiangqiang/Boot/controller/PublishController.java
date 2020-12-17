package com.qiangqiang.Boot.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.dubbo.config.annotation.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private Queue newsQueue;

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public final String url = "http://news.china.com.cn/node_7183219.htm";

    public static final String division = "@_@";
    @RequestMapping("/new")
    public void jsoupNewsToMQ() throws JMSException {

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(newsQueue);
        TextMessage textMessage = session.createTextMessage();


        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements listDiv = doc.getElementsByAttributeValue("class", "Headlines");
            for (Element element : listDiv) {
                Elements texts = element.getElementsByTag("li");
                for (Element text : texts) {

                    // 取所有文本
                    // String ptext = text.text();
                    Elements a = text.getElementsByTag("a");
                    String text1 = a.text();
                    if(!text1.equals("")){
                        StringBuffer sb = new StringBuffer();
                        System.out.println("标题：" + text1);
                        String href = a.attr("href");
                        StringBuffer stringBuffer = new StringBuffer();
                        Document doc1 = Jsoup.connect(href).get();
                        Elements listDiv1 = doc1.getElementsByAttributeValue("class", "leftBox");
                        for (Element element01 : listDiv1){
                            Elements elementsByAttributeValue = element01.getElementsByAttributeValue("class", "articleBody");
                            for (Element element1 : elementsByAttributeValue) {
                                Elements p = element1.getElementsByTag("p");
                                for (Element element2 : p) {
                                    String text2 = element2.text();
                                    stringBuffer.append(text2);
                                }

                            }
                        }
//                        System.out.println(stringBuffer.toString());

                        String dateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);


                        StringBuffer append = sb.append(text1).append(division).append(stringBuffer.toString()).append(division).append(dateTime);
                        String info = append.toString();

                        textMessage.setText(info);
                        producer.send(textMessage);

                    }
                }
            }
            Elements elementsByAttributeValue = doc.getElementsByAttributeValue("id", "autopage");
            for (Element element : elementsByAttributeValue) {
                Elements center = element.getElementsByTag("center");
                for (Element element1 : center) {
                    Elements a = element1.getElementsByTag("a");
                    for (Element element2 : a) {
                        String href = element2.attr("href");
                        Document doc1 = Jsoup.connect(href).get();
                        Elements listDiv1 = doc1.getElementsByAttributeValue("class", "Headlines");
                        for (Element element01 : listDiv1) {
                            Elements texts = element01.getElementsByTag("li");
                            for (Element text : texts) {

                                // 取所有文本
                                // String ptext = text.text();
                                Elements a01 = text.getElementsByTag("a");
                                String text1 = a01.text();
                                if(!text1.equals("")){
                                    StringBuffer sb = new StringBuffer();
                                    System.out.println("标题：" + text1);
                                    String href2 = a01.attr("href");
                                    StringBuffer stringBuffer = new StringBuffer();
                                    Document doc12 = Jsoup.connect(href2).get();
                                    Elements listDiv12 = doc12.getElementsByAttributeValue("class", "leftBox");
                                    for (Element element012 : listDiv12){
                                        Elements elementsByAttributeValue2 = element012.getElementsByAttributeValue("class", "articleBody");
                                        for (Element element12 : elementsByAttributeValue2) {
                                            Elements p = element12.getElementsByTag("p");
                                            for (Element element22 : p) {
                                                String text2 = element22.text();
                                                stringBuffer.append(text2);
                                            }

                                        }
                                    }
//                                    System.out.println(stringBuffer.toString());
                                    String dateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);


                                    StringBuffer append = sb.append(text1).append(division).append(stringBuffer.toString()).append(division).append(dateTime);
                                    String info = append.toString();

                                    textMessage.setText(info);
                                    producer.send(textMessage);
                                }
                            }
                        }

                    }

                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



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