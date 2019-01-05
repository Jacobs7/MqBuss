package com.dp.service;

import com.dp.constants.constants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import sun.security.krb5.internal.crypto.Des;

import javax.jms.*;


public class MqTopicRec {


    public static void main(String[] args){

        ConnectionFactory connectionFactory;

        Connection connection;

        Session session ;

        Destination destination;

        MessageConsumer messageConsumer;

        connectionFactory =new ActiveMQConnectionFactory(constants.MQ_NAME,constants.MQ_PASSWORD,constants.MQ_BROKETURL);

        try {
            connection = connectionFactory.createConnection();

            connection.start();

            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            destination = session.createTopic("my-topic");

            messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String tx = ((TextMessage )message).getText();
                        System.out.println("recive:"+tx);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
