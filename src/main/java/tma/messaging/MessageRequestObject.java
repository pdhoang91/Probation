package tma.messaging;

import java.io.Serializable;

import tma.model.Order;

public class MessageRequestObject implements Serializable{
	
	private static final long serialVersionUID = -295422703255886286L;
	private String orderId;
	private MessageRequestType requestType;
	private Order order;
	
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MessageRequestObject(MessageRequestType requestType, Order order) {
		this.order = order;
		this.requestType = requestType;
	}
	
	public MessageRequestObject(MessageRequestType requestType, String orderId) {
		this.orderId = orderId;
		this.requestType = requestType;
	}
	
	public MessageRequestObject(MessageRequestType requestType) {
		this.requestType = requestType;
	}

	public MessageRequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(MessageRequestType requestType) {
		this.requestType = requestType;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageRequestObject other = (MessageRequestObject) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (requestType != other.requestType)
			return false;
		return true;
	}
	
	
}
