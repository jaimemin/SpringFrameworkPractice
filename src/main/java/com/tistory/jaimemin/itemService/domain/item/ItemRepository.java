package com.tistory.jaimemin.itemService.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // MultiThread 환경에서는 ConcurrentHashMap
    private static final Map<Long, Item> storage = new HashMap<>();

    // 동시에 접근할 경우 이렇게 사용하면 안됨
    private static long sequence = 1L;

    public Item save(Item item) {
        item.setId(sequence++);

        storage.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return storage.get(id);
    }

    public List<Item> findAll() {
        // 감싸서 반환하게 되면 ArrayList를 변경하더라도 storage에 영향 안줌
        return new ArrayList<>(storage.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item foundItem = findById(itemId);

        // 프로젝트 규모 커지면 id를 사용하지 않으므로 새로운 객체 생성하는 것이 맞음
        foundItem.setItemName(updateParam.getItemName());
        foundItem.setPrice(updateParam.getPrice());
        foundItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStorage() {
        storage.clear();
    }
}
