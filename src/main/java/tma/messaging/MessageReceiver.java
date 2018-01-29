package tma.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import tma.service.MessageCallRepository;
 
@Component
public class MessageReceiver {

	@Autowired
    MessageCallRepository messageCallRepository;
    
    
//    @JmsListener(destination = "MessageQueue")
//    public <T> void receiveMessageQueue(T requestObject){    	
//    	if (requestObject instanceof MessageRequestObject){
//    		MessageRequestObject messageRequestObject = (MessageRequestObject) requestObject;
//    		if (messageRequestObject.getRequestType().equals(MessageRequestType.AddingAnOrder)){
//    			  messageCallRepository.sendOrder(((MessageRequestObject) requestObject).getOrder());
//    		}	
//    	}
//    }
    
    
    @JmsListener(destination = "MessageQueue")
    public void receiveMessageQueue(MessageRequestObject requestObject){
    	
    
    		if (requestObject.getRequestType().equals(MessageRequestType.AddingAnOrder)){
    			  messageCallRepository.sendOrder(((MessageRequestObject) requestObject).getOrder());
    		}
    		if (requestObject.getRequestType().equals(MessageRequestType.UpdateOrder)){
    			messageCallRepository.updateOrder(((MessageRequestObject) requestObject).getOrder());
    		}
    		if (requestObject.getRequestType().equals(MessageRequestType.DeleteOrderById)){
    			messageCallRepository.deleteOrderById(requestObject.getOrderId());
    		}
    		if (requestObject.getRequestType().equals(MessageRequestType.DeleteAll)){
    			messageCallRepository.deleteAllOrders();
    		}
    }
}


