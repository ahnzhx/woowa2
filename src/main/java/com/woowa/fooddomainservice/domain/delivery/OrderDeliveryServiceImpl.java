package com.woowa.fooddomainservice.domain.delivery;

import org.springframework.stereotype.Component;

import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderDeliveredService;
import com.woowa.fooddomainservice.domain.order.OrderPayedService;
import com.woowa.fooddomainservice.domain.order.OrderRepository;
import com.woowa.fooddomainservice.domain.shop.Shop;
import com.woowa.fooddomainservice.domain.shop.ShopRepository;

@Component
public class OrderDeliveryServiceImpl implements OrderPayedService, OrderDeliveredService {
    private OrderRepository orderRepository;
	private DeliveryRepository deliveryRepository;
    private ShopRepository shopRepository;
    

	public OrderDeliveryServiceImpl(OrderRepository orderRepository,
									DeliveryRepository deliveryRepository,
									ShopRepository shopRepository) {
		this.orderRepository = orderRepository;
		this.deliveryRepository = deliveryRepository;
		this.shopRepository = shopRepository;
	}

	@Override
    public void deliverOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.payed();
		
		Delivery delivery = Delivery.started(orderId);
		deliveryRepository.save(delivery);
    }

    @Override
    public void payOrder(Long orderId) {
    	Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
    	//Shop shop = shopRepository.findById(order.getShopId()) 
		// @formatter:on
    	
    }
}
