package com.everyday.services;

import com.everyday.model.Item;
import com.everyday.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItemList(Integer boardKey) {
        return itemRepository.findAllByBoardKeyAndStatusOrderByCreateDateDesc(boardKey, true);
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Integer itemKey) {
        itemRepository.deleteByItemKey(itemKey);
    }

    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    public Item getItem(int itemKey) {
        return itemRepository.findById(itemKey);
    }
}
