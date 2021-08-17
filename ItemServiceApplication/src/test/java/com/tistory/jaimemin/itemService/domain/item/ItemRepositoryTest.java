package com.tistory.jaimemin.itemService.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clearStorage() {
        itemRepository.clearStorage();
    }

    @Test
    void save() {
        // given
        Item item = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item foundItem = itemRepository.findById(item.getId());

        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        Item item2 = Item.builder()
                .itemName("itemB")
                .price(1000)
                .quantity(20)
                .build();

        itemRepository.save(item);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = Item.builder()
                .itemName("itemB")
                .price(1000)
                .quantity(20)
                .build();

        itemRepository.update(itemId, updateParam);

        // then
        Item foundItem = itemRepository.findById(itemId);

        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}