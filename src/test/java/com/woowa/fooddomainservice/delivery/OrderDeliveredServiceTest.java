package com.woowa.fooddomainservice.delivery;

import com.woowa.fooddomainservice.domain.delivery.Delivery;
import com.woowa.fooddomainservice.domain.delivery.DeliveryRepository;
import com.woowa.fooddomainservice.domain.delivery.OrderDeliveryServiceImpl;
import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.generic.money.Ratio;
import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderDeliveredService;
import com.woowa.fooddomainservice.domain.order.OrderRepository;
import com.woowa.fooddomainservice.domain.shop.Shop;
import com.woowa.fooddomainservice.domain.shop.ShopRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.woowa.fooddomainservice.Fixtures.*;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderDeliveredServiceTest {
    @Mock private OrderRepository orderRepository;
    @Mock private ShopRepository shopRepository;
    @Mock private DeliveryRepository deliveryRepository;

    private OrderDeliveredService orderDeliveredService;

    @Before
    public void setUp(){
        orderDeliveredService = new OrderDeliveryServiceImpl(orderRepository, deliveryRepository, shopRepository);
    }

    @Test
    public void 주문완료(){
        Shop shop = aShop()
                        .commissionRate(Ratio.valueOf(0.01))
                        .commission(Money.wons(1000))
                        .build();
        Order order = anOrder().items(asList(
                anOrderLineItem().groups(asList(
                        anOrderOptionGroup().options(asList(
                                anOrderOption().price(Money.wons(10000)).build())).build()))
                        .build()))
                .build();

        Delivery delivery = aDelivery().build();

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(shopRepository.findById(shop.getId())).thenReturn(Optional.of(shop));
        when(deliveryRepository.findById(delivery.getId())).thenReturn(Optional.of(delivery));
        when(deliveryRepository.findById(delivery.getId())).thenReturn(Optional.of(delivery));

        orderDeliveredService.deliverOrder(order.getId());
        //TODO: 에러나는거 해결하기
        System.out.println(order.getOrderStatus());
        System.out.println(delivery.getDeliveryStatus());
        System.out.println(shop.getCommission());



//
//        assertThat(order.getOrderStatus(), is(Order.OrderStatus.DELIVERED));
//        assertThat(delivery.getDeliveryStatus(), is(Delivery.DeliveryStatus.DELIVERED));
//        assertThat(shop.getCommission(), is(Money.wons(1100)));
    }


}
