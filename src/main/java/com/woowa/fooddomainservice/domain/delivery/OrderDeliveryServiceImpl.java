package com.woowa.fooddomainservice.domain.delivery;

import com.woowa.fooddomainservice.domain.order.OrderDeliveredService;
import com.woowa.fooddomainservice.domain.order.OrderPayedService;
import org.springframework.stereotype.Component;

@Component
public class OrderDeliveryServiceImpl implements OrderPayedService, OrderDeliveredService {
    private DeliveryRepository deliveryRepository;

    @Override
    public void deliverOrder(Long orderId) {

    }

    @Override
    public void payOrder(Long orderId) {

    }
}
