/**
 * 
 */
package com.woowa.fooddomainservice.service.order;

import javax.transaction.Transactional;

import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderDeliveredService;
import com.woowa.fooddomainservice.domain.order.OrderPayedService;
import com.woowa.fooddomainservice.domain.order.OrderRepository;
import com.woowa.fooddomainservice.domain.order.OrderValidator;

/**
 * packageName	: com.woowa.fooddomainservice.service.order
 * fileName	: OrderService.java 
 * author		: twayair
 * date		: 2019. 8. 8.
 * 내용			: 
 *
 * ===========================================================
 *
 * DATE				AUTHOR			NOTE
 * -----------------------------------------------------------
 * 2019. 8. 8.			twayair			최초 생성
 */
public class OrderService {
	private OrderRepository orderRepository;
	private OrderValidator orderValidator;
	private OrderMapper orderMapper;
	private OrderDeliveredService orderDeliveredService;
	private OrderPayedService orderPayedService;
	
	public OrderService(OrderRepository orderRepository, 
						OrderValidator orderValidator, 
						OrderMapper orderMapper,
						OrderDeliveredService orderDeliveredService, 
						OrderPayedService orderPayedService) {
		this.orderRepository = orderRepository;
		this.orderValidator = orderValidator;
		this.orderMapper = orderMapper;
		this.orderDeliveredService = orderDeliveredService;
		this.orderPayedService = orderPayedService;
	}
	
	@Transactional
	public void placeOrder(Cart cart) {
		Order order = orderMapper.mapFrom(cart);
		order.place(orderValidator);
		orderRepository.save(order);
	}
	
	@Transactional
	public void payOrder(Long orderId) {
		orderPayedService.payOrder(orderId);
	}
	
	@Transactional
	public void deliverOrder(Long orderId) {
		orderDeliveredService.deliverOrder(orderId);
	}
}
