package com.dp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ProducerService {

    @Autowired
    private JmsTemplate jmsTemplatePool;


    public void sendMsg(Destination destination, final String message){
        jmsTemplatePool.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {


                return session.createTextMessage(message);
            }
        });
    }

}
