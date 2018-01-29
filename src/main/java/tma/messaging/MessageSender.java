package tma.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
 
 
@Component
public class MessageSender {
	
    @Autowired
    JmsTemplate jmsTemplate;

	public void sendMessageAdd(MessageRequestObject requestObject) {
		jmsTemplate.convertAndSend(MessageConstant.MESSAGE_QUEUE_NAME, requestObject);
	}
	
	public void sendMessageDeleteById(MessageRequestObject requestObject) {
		jmsTemplate.convertAndSend(MessageConstant.MESSAGE_QUEUE_NAME, requestObject);
	}

	public void sendMessageUpdate(MessageRequestObject requestObject) {
		jmsTemplate.convertAndSend(MessageConstant.MESSAGE_QUEUE_NAME, requestObject);
	}

	public void sendMessageDeleteAllOrders(MessageRequestObject requestObject) {
		jmsTemplate.convertAndSend(MessageConstant.MESSAGE_QUEUE_NAME,requestObject);
	}
}