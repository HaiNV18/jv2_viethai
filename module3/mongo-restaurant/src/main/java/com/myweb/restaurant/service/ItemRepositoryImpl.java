package com.myweb.restaurant.service;

import com.mongodb.client.result.UpdateResult;
import com.myweb.restaurant.model.Item;
import com.myweb.restaurant.repository.ItemRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateRestaurantId(String oldId, String newId) {
        Query query = new Query(Criteria.where("restaurant_id").is(oldId));
        Update update = new Update().set("restaurant_id", newId);
        UpdateResult result = mongoTemplate.updateMulti(query, update, Item.class);
        System.out.println("Updated " + result.getModifiedCount() + " items");
    }

}
