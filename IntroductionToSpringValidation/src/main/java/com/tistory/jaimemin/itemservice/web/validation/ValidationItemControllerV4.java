package com.tistory.jaimemin.itemservice.web.validation;

import com.tistory.jaimemin.itemservice.domain.item.Item;
import com.tistory.jaimemin.itemservice.domain.item.ItemRepository;
import com.tistory.jaimemin.itemservice.domain.item.SaveCheck;
import com.tistory.jaimemin.itemservice.domain.item.UpdateCheck;
import com.tistory.jaimemin.itemservice.web.validation.form.ItemSaveForm;
import com.tistory.jaimemin.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
@Slf4j
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());

        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        // 특정 필드가 아닌 복합 룰 검증
        if (!ObjectUtils.isEmpty(form.getPrice()) && !ObjectUtils.isEmpty(form.getQuantity())) {
            int resultPrice = form.getPrice() * form.getQuantity();

            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item"
                        , "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        // 검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            return "validation/v4/addForm";
        }

        // 성공 로직
        Item savedItem = itemRepository.save(Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .build());
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/validation/v4/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v4/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId
            , @Validated @ModelAttribute("item") ItemUpdateForm form
            , BindingResult bindingResult) {
        // 특정 필드가 아닌 복합 룰 검증
        if (!ObjectUtils.isEmpty(form.getPrice()) && !ObjectUtils.isEmpty(form.getQuantity())) {
            int resultPrice = form.getPrice() * form.getQuantity();

            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item"
                        , "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        // 검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);

            return "validation/v4/editForm";
        }

        itemRepository.update(itemId, Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .build());

        return "redirect:/validation/v4/items/{itemId}";
    }

}

