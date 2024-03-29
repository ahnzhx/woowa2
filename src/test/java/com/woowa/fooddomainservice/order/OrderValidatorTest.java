package com.woowa.fooddomainservice.order;

import com.woowa.fooddomainservice.domain.generic.money.Money;
import com.woowa.fooddomainservice.domain.order.Order;
import com.woowa.fooddomainservice.domain.order.OrderValidator;
import com.woowa.fooddomainservice.domain.shop.Menu;
import com.woowa.fooddomainservice.domain.shop.MenuRepository;
import com.woowa.fooddomainservice.domain.shop.Shop;
import com.woowa.fooddomainservice.domain.shop.ShopRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;

import static com.woowa.fooddomainservice.Fixtures.*;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

public class OrderValidatorTest {
    private OrderValidator validator;

    @Before
    public void setUp(){
        validator = new OrderValidator(mock(ShopRepository.class), mock(MenuRepository.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 가게_준비중인경우_실패(){
        Shop shop = aShop().open(false).build();
        validator.validate(anOrder().build(), shop, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void 최소주문금액_이하인경우_실패(){
        Order order = anOrder().items(asList(
                        anOrderLineItem().count(1).groups(asList(
                                anOrderOptionGroup().options(asList(
                                        anOrderOption().price(Money.wons(12000)).build())).build()
                                )).build()))
                        .build();
        Shop shop = aShop().open(false).minOrderAmount(Money.wons(13000)).build();
        validator.validate(order, shop, new HashMap<>());

    }

    @Test(expected = IllegalArgumentException.class)
    public void 메뉴옵션그룹이_변경된경우_실패(){
        Menu menu = aMenu()
                        .basic(anOptionGroupSpec().name("그룹명").build())
                        .build();

        Order order = anOrder().items(asList(
                        anOrderLineItem()
                            .groups(asList(anOrderOptionGroup().name("그룹명변경").build()))
                            .build()))
                        .build();

        validator.validate(order, aShop().build(), new HashMap<Long, Menu>(){{put(1L, menu);}});
    }

    @Test(expected =  IllegalArgumentException.class)
    public void 메뉴옵션이_변경된경우_실패(){
        Menu menu = aMenu()
                        .basic(anOptionGroupSpec()
                            .options(asList(anOptionSpec().name("옵션").build()))
                            .build())
                        .build();

        Order order = anOrder().items(asList(
                anOrderLineItem().groups(asList(
                        anOrderOptionGroup().options(asList(
                                anOrderOption().name("옵션변경").price(Money.wons(12000)).build()
                        )).build()
                )).build()
        )).build();

        validator.validate(order, aShop().build(), new HashMap<Long, Menu>(){{ put(1L, menu); }});

    }
}
