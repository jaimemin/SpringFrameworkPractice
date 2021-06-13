package com.tistory.jaimemin.itemService.domain.web.basic;

import com.tistory.jaimemin.itemService.domain.item.Item;
import com.tistory.jaimemin.itemService.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());

        return "basic/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build());
        itemRepository.save(Item.builder()
                .itemName("itemB")
                .price(2000)
                .quantity(20)
                .build());
    }
}
