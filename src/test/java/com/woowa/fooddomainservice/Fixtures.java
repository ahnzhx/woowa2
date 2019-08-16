package com.woowa.fooddomainservice;

import com.woowa.fooddomainservice.domain.delivery.Delivery;
import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.generic.money.Ratio;
import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderLineItem;
import com.woowa.fooddomainservice.domain.order.OrderOption;
import com.woowa.fooddomainservice.domain.order.OrderOptionGroup;
import com.woowa.fooddomainservice.domain.shop.*;
import com.woowa.fooddomainservice.domain.shop.Shop.ShopBuilder;
import com.woowa.fooddomainservice.domain.shop.Menu.MenuBuilder;
import com.woowa.fooddomainservice.domain.shop.Option.OptionBuilder;
import com.woowa.fooddomainservice.domain.shop.OptionGroup.OptionGroupBuilder;
import com.woowa.fooddomainservice.domain.shop.OptionGroupSpecification.OptionGroupSpecificationBuilder;
import com.woowa.fooddomainservice.domain.shop.OptionSpecification.OptionSpecificationBuilder;
import com.woowa.fooddomainservice.domain.order.Order.OrderBuilder;
import com.woowa.fooddomainservice.domain.delivery.Delivery.DeliveryBuilder;
import com.woowa.fooddomainservice.domain.order.OrderLineItem.OrderLineItemBuilder;
import com.woowa.fooddomainservice.domain.order.OrderOptionGroup.OrderOptionGroupBuilder;
import com.woowa.fooddomainservice.domain.order.OrderOption.OrderOptionBuilder;


import java.time.LocalDateTime;
import java.util.Arrays;


public class Fixtures {
	public static ShopBuilder aShop() {
		return Shop.builder()
				.id(1L)
				.name("오겹돼지")
				.commissionRate(Ratio.valueOf(0.01))
				.open(true)
				.minOrderAmount(Money.wons(13000))
				.commission(Money.ZERO);
	}

	public static MenuBuilder aMenu() {
		return Menu.builder()
				.id(1L)
				.shopId(aShop().build().getId())
				.name("삼겹살 1인세트")
				.description("삼겹살 + 야채세트 + 김치찌개")
				.basic(anOptionGroupSpec()
						.name("기본")
						.options(Arrays.asList(anOptionSpec().name("소(250g)").price(Money.wons(12000)).build()))
						.build())
				.additives(Arrays.asList(
						anOptionGroupSpec()
								.basic(false)
								.name("맛선택")
								.options(Arrays.asList(anOptionSpec().name("매콤 맛").price(Money.wons(1000)).build()))
								.build()));
	}

	public static OptionGroupSpecificationBuilder anOptionGroupSpec() {
		return OptionGroupSpecification.builder()
				.basic(true)
				.exclusive(true)
				.name("기본")
				.options(Arrays.asList(anOptionSpec().build()));
	}

	public static OptionSpecificationBuilder anOptionSpec() {
		return OptionSpecification.builder()
				.name("소(250g)")
				.price(Money.wons(12000));
	}

	public static OptionGroupBuilder anOptionGroup() {
		return OptionGroup.builder()
				.name("기본")
				.options(Arrays.asList(anOption().build()));
	}

	public static OptionBuilder anOption() {
		return Option.builder()
				.name("소(250g)")
				.price(Money.wons(12000));
	}

	public static OrderBuilder anOrder() {
		return Order.builder()
				.id(1L)
				.userId(1L)
				.shopId(aShop().build().getId())
				.status(Order.OrderStatus.ORDERED)
				.orderedTime(LocalDateTime.of(2020, 1, 1, 12, 0))
				.items(Arrays.asList(anOrderLineItem().build()));
	}

	public static OrderLineItemBuilder anOrderLineItem() {
		return OrderLineItem.builder()
				.menuId(aMenu().build().getId())
				.name("삼겹살 1인세트")
				.count(1)
				.groups(Arrays.asList(
						anOrderOptionGroup()
								.name("기본")
								.options(Arrays.asList(anOrderOption().name("소(250g)").price(Money.wons(12000)).build()))
								.build(),
						anOrderOptionGroup()
								.name("맛선택")
								.options(Arrays.asList(anOrderOption().name("매콤 맛").price(Money.wons(1000)).build()))
								.build()));
	}

	public static OrderOptionGroupBuilder anOrderOptionGroup() {
		return OrderOptionGroup.builder()
				.name("기본")
				.options(Arrays.asList(anOrderOption().build()));
	}

	public static OrderOptionBuilder anOrderOption() {
		return OrderOption.builder()
				.name("소(250g)")
				.price(Money.wons(12000));
	}

	public static DeliveryBuilder aDelivery() {
		return Delivery.builder()
				.id(1L)
				.deliveryStatus(Delivery.DeliveryStatus.DELIVERING)
				.orderId(anOrder().build().getId());
	}


}
