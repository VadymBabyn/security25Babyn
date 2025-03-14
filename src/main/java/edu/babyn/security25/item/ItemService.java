package edu.babyn.security25.item;

/*
@author   vadim
@project   security25
@class  ItemService
@version  1.0.0
@since 14.03.2025 - 11.58
*/

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ItemService {
    private final ItemRepository repository;

    private List<Item> items;
    @PostConstruct
    void init() {
        items.add(new Item("1","Name1","Des1"));
        items.add(new Item("2","Name2","Des2"));
        items.add(new Item("3","Name3","Des3"));
        repository.saveAll(items);
    }

    public List<Item> getAll() {
        return repository.findAll();
    }


    public Item getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Item create(Item item) {
        return repository.save(item);
    }

    public Item update(Item item) {
        return repository.save(item);
    }
}
