package edu.babyn.security25.item;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/helloadmin")
    public String helloAdmin()
    {
        return "Hello Admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hellouser")
    public String helloUser()
    {
        return "Hello User";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/helloguest")
    public String helloGuest()
    {
       return "Hello Guest";
    }

    @GetMapping("/helloeveryone")
    public String helloEveryone()
    {
        return "Hello Everyone";
    }
}
