package com.peng.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    private static final String url = "tcp://127.0.0.1:61616";
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2.创建连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话 第一个参数是是否使用事务，第二个参数是会话的应答模式-自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目标对象
        Destination destination = session.createTopic(topicName);
        //6.创建生产者
        MessageProducer producer = session.createProducer(destination);
        //7.创建消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("testTopic" + i);
            producer.send(textMessage);
            System.out.println("发送的消息："+textMessage.getText());
        }
        //8.释放连接
        connection.close();
    }
}
