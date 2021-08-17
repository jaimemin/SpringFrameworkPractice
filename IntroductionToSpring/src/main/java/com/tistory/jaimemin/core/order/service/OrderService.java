package com.tistory.jaimemin.core.order.service;

import com.tistory.jaimemin.core.order.entity.Order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
