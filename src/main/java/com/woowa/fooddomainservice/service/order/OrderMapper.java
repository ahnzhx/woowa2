/**
 * 
 */
package com.woowa.fooddomainservice.service.order;

import org.springframework.stereotype.Component;

import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderLineItem;
import com.woowa.fooddomainservice.domain.order.OrderOption;
import com.woowa.fooddomainservice.domain.order.OrderOptionGroup;

import static java.util.stream.Collectors.toList;

import java.util.stream.Collectors;
/**
 * packageName	: com.woowa.fooddomainservice.service.order
 * fileName	: OrderMapper.java 
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
@Component
public class OrderMapper {
	public Order mapFrom(Cart cart) {
		return new Order(
				cart.getUserId(),
				cart.getShopId(),
				cart.getCartLineItems()
					.stream()
					.map(this::toOrderLineItem)
					.collect(toList()));
		
	}

	private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem) {
		return new OrderLineItem(
				cartLineItem.getMenuId(),
				cartLineItem.getName(),
				cartLineItem.getCount(),
				cartLineItem.getGroups()
					.stream()
					.map(this::toOrderOptionGroup)
					.collect(Collectors.toList()));
	}
	
	private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup) {
		return new OrderOptionGroup(
				cartOptionGroup.getName(),
				cartOptionGroup.getOptions()
					.stream()
					.map(this::toOrderOption)
					.collect(Collectors.toList()));
	}
	
	private OrderOption toOrderOption(Cart.CartOption cartOption) {
		return new OrderOption(
				cartOption.getName(),
				cartOption.getPrice());
	}
}
