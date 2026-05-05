package com.myweb.restaurant.service;

import com.myweb.restaurant.model.Item;
import com.myweb.restaurant.model.Restaurant;
import com.myweb.restaurant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ItemService {

    @Autowired
    public ItemRepository itemRepo;

    public Page<Item> findAllPagination(Pageable pageable) {
        return itemRepo.findAll(pageable);
    }

    public Item findByItemId(String id) {
        return itemRepo.findByItemId(id).orElse(null);
    }

    public Item save(Item newObject) {
        return itemRepo.save(newObject);
    }

    public Item updateItem(Item oldObject, Item newObject){
        if (Objects.nonNull(newObject.getName())){
            oldObject.setName(newObject.getName());
        }
        if (Objects.nonNull(newObject.getPrice())){
            oldObject.setPrice(newObject.getPrice());
        }
        return itemRepo.save(oldObject);
    }


}
