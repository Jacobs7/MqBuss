package com.dp.service;

import com.dp.constants.constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.JmsException;

import javax.jms.*;

public class MqQueueRec {

    ConnectionFactory connectionFactory ;

    Connection connection;

    Session session;

    Destination destination;



    public static void main(String[] args){

        //连接工厂
        ConnectionFactory connectionFactory;
        //连接对象
        Connection connection ;
        //收发对象
        Session session;
        //发消息的目标
        Destination destination;
        //消息接收者
        MessageConsumer messageConsumer;

        //连接工厂实例化
        connectionFactory =new ActiveMQConnectionFactory(constants.MQ_NAME, constants.MQ_PASSWORD,constants.MQ_BROKETURL);



        try {
            //连接对象实例化
            connection = connectionFactory.createConnection();

            connection.start();
            //会话对象实例化
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("my-act");

            messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String tx = ((TextMessage)message).getText();
                        System.out.println("tx:"+tx);

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });



        } catch (JMSException exception) {

            exception.printStackTrace();
        }


    }


}
