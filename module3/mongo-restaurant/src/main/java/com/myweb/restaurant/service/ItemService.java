package com.myweb.restaurant.service;

import com.myweb.restaurant.model.Item;
import com.myweb.restaurant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    public ItemRepository itemRepo;

    public Page<Item> findAllPagination(Pageable pageable) {
        return itemRepo.findAll(pageable);
    }
}
