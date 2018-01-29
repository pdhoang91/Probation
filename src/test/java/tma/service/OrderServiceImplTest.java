package tma.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tma.configuration.ApplicationConfiguration;
import tma.configuration.SpringMvcInitializer;
import tma.messaging.MessageRequestObject;
import tma.messaging.MessageRequestType;
import tma.messaging.MessageSender;
import tma.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, ApplicationConfiguration.class})
@WebAppConfiguration
public class OrderServiceImplTest {
	
	Order order = new Order("id","cocacola",123);
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	@Mock
	OrderRepository orderRepository;	
	
	@Mock
    MessageSender messageSender;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
		@Test
		public void testGetAllOrder() {
			orderServiceImpl.getAllOrders();
			Mockito.verify(orderRepository).getAllOrders();
		}
		
		@Test
		public void testGetAllOrderAnother() {
			List<Order> orders = Arrays.asList(
		            new Order(),
		            new Order()
		        );
		        when(orderServiceImpl.getAllOrders()).thenReturn(orders);
		        assertEquals("getAllOrders should return two orders",2,orderServiceImpl.getAllOrders().size());
		}
		
		@Test
		public void testGetOrderById(){
			orderServiceImpl.getOrderById("1");
			Mockito.verify(orderRepository).getOrderById(Mockito.any(Query.class));
		}
		
		@Test
		public void testSendOrder() {
			orderServiceImpl.sendOrder(order);
			Mockito.verify(messageSender).sendMessageAdd(Mockito.eq(new MessageRequestObject(MessageRequestType.AddingAnOrder, order)));
		}
		
		@Test
		public void testUpdateOrder(){
			orderServiceImpl.updateOrder(order);
			Mockito.verify(messageSender).sendMessageUpdate(Mockito.eq(new MessageRequestObject(MessageRequestType.UpdateOrder, order)));
		}
		
		@Test
		public void testDeleteOrderById(){
			orderServiceImpl.deleteById("abcxyz");
			Mockito.verify(messageSender).sendMessageDeleteById(Mockito.eq(new MessageRequestObject(MessageRequestType.DeleteOrderById ,"abcxyz")));
		}
		
		@Test
		public void testDeleteAllOrder_PassCase() {
			orderServiceImpl.deleteAllOrders();
			Mockito.verify(messageSender).sendMessageDeleteAllOrders(Mockito.eq(new MessageRequestObject(MessageRequestType.DeleteAll)));
		}
}











