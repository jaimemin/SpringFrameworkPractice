package com.tistory.jaimemin.core.order.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Order {

    private Long memberId;

    private String itemName;

    private int itemPrice;

    private int discountPrice;

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
