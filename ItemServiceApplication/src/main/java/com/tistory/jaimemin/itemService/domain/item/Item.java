package com.tistory.jaimemin.itemService.domain.item;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Item {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;
}
