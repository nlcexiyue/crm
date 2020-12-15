package com.qiangqiang.Boot.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJcaListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 11:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class ActiveMQConfig {
    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    //初始化队列
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }

    //初始化主题
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }

    //初始化连接工行
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory(usrName,password,brokerUrl);
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactoryQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactoryTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        //更改为topic模式
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }










}