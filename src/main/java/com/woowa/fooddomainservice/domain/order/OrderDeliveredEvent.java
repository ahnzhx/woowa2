/**
 * 
 */
package com.woowa.fooddomainservice.domain.order;

import com.woowa.fooddomainservice.domain.generic.money.Money;

/**
 * packageName	: com.woowa.fooddomainservice.domain.order
 * fileName	: OrderDeliveredEvent.java 
 * author		: twayair
 * date		: 2019. 9. 4.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 9. 4.			twayair			최초 생성
 */
public class OrderDeliveredEvent {
	private Order order;
	public OrderDeliveredEvent(Order order) {
		this.order = order;
	}
	
	public Long getOrderId() {
		return order.getId();
	}
	
	public Long getShopId() {
		return order.getShopId();
	}
	
	public Money getTotalPrice() {
		return order.calculateTotalPrice();
	}
}
