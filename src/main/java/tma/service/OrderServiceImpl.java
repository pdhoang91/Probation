package tma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import tma.messaging.MessageRequestObject;
import tma.messaging.MessageRequestType;
import tma.messaging.MessageSender;
import tma.model.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
    MessageSender messageSender;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public void sendOrder(Order order) {
		MessageRequestObject requestObject = new MessageRequestObject(MessageRequestType.AddingAnOrder, order);
		messageSender.sendMessageAdd(requestObject);
	}
	
	@Override
	public void updateOrder(Order orderUpdate) {
		MessageRequestObject requestObject = new MessageRequestObject(MessageRequestType.UpdateOrder, orderUpdate);
		messageSender.sendMessageUpdate(requestObject);
	}
	
	@Override
	public void deleteById(String orderId) {
		MessageRequestObject requestObject = new MessageRequestObject(MessageRequestType.DeleteOrderById , orderId);
		messageSender.sendMessageDeleteById(requestObject);
	}	
	
	@Override
	public void deleteAllOrders() {		
		MessageRequestObject requestObject = new MessageRequestObject(MessageRequestType.DeleteAll);
		messageSender.sendMessageDeleteAllOrders(requestObject);
	}
	
	public List<Order> getAllOrders(){
		return orderRepository.getAllOrders();
	}
	
	@Override
	public Order getOrderById(String orderId) {		
		Query QueryGetId = new Query(Criteria.where("_id").is(orderId));
		return orderRepository.getOrderById(QueryGetId);
	}
}
