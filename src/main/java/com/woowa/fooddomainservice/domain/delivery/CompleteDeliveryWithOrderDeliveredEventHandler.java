/**
 * 
 */
package com.woowa.fooddomainservice.domain.delivery;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.woowa.fooddomainservice.domain.order.OrderDeliveredEvent;

/**
 * packageName	: com.woowa.fooddomainservice.domain.delivery
 * fileName	: CompleteDeliveryWithOrderDeliveredEventHandler.java 
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
@Component
public class CompleteDeliveryWithOrderDeliveredEventHandler {
	private DeliveryRepository deliveryRepository;

	public CompleteDeliveryWithOrderDeliveredEventHandler(DeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}
	
	@Async
	@EventListener
	@Transactional
	public void handle(OrderDeliveredEvent event) {
		Delivery delivery = deliveryRepository.findById(event.getOrderId()).orElseThrow(IllegalArgumentException::new);
		delivery.complete();
	}
}
