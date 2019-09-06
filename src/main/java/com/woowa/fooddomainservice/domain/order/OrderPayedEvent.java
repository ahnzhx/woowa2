/**
 * 
 */
package com.woowa.fooddomainservice.domain.order;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OrderPayedEvent.java 
 * author		: twayair
 * date		: 2019. 9. 6.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 9. 6.			twayair			최초 생성
 */
public class OrderPayedEvent {
	private Order order;
	
	public OrderPayedEvent(Order order) {
		this.order = order;
	}
	
	public Long getOrderId() {
		return order.getId();
	}

}
