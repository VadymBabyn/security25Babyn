package edu.babyn.security25.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@author   vadim
@project   security25
@class  itemController
@version  1.0.0
@since 14.03.2025 - 11.59
*/
@RestController
@RequestMapping("/api/v1/items")
@AllArgsConstructor

public class ItemRestController
{
    private final ItemService service;

    @GetMapping
    public List<Item> getItems()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item getOneItems(@PathVariable String id)
    {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id)
    {
        service.deleteById(id);
    }

    @PostMapping
    public Item create(@RequestBody Item item)
    {
        return service.create(item);
    }

    @PutMapping
    public Item update(@RequestBody Item item)
    {
        return service.update(item);
    }

    @GetMapping("/name/{id}")
    public String getNameById(@PathVariable String id)
    {
        return service.getNameById(id);
    }
    @GetMapping("/helloadmin")
    public String helloAdmin()
    {
        return "Hello Admin";
    }
    @GetMapping("/hellouser")
    public String helloUser()
    {
        return "Hello User";
    }
    @GetMapping("/helloguest")
    public String helloGuest()
    {
       return "Hello Guest";
    }
}
