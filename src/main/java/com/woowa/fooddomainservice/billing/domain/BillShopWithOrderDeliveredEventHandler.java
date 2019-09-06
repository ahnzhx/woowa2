/**
 * 
 */
package com.woowa.fooddomainservice.billing.domain;

import javax.transaction.Transactional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.woowa.fooddomainservice.domain.order.OrderDeliveredEvent;
import com.woowa.fooddomainservice.domain.shop.Shop;
import com.woowa.fooddomainservice.domain.shop.ShopRepository;

/**
 * packageName	: com.woowa.fooddomainservice.billing.domain
 * fileName	: BillShopWithOrderDeliveredEventHandler.java 
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
@Component
public class BillShopWithOrderDeliveredEventHandler {
	private ShopRepository shopRepository;
	private BillingRepository billingRepository;
	
	public BillShopWithOrderDeliveredEventHandler(ShopRepository shopRepository, BillingRepository billingRepository) {
		this.shopRepository = shopRepository;
		this.billingRepository = billingRepository;
	}
	
	@Async
	@EventListener
	@Transactional
	public void handle(OrderDeliveredEvent event) {
		Shop shop = shopRepository.findById(event.getShopId())
				.orElseThrow(IllegalArgumentException::new);
		Billing billing = billingRepository.findByShopId(event.getShopId())
				.orElse(new Billing(event.getShopId()));
		
		billing.billCommissionFee(shop.calculateCommissionFee(event.getTotalPrice()));
	}
	
}
