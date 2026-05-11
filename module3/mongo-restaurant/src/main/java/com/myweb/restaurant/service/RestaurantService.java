package com.myweb.restaurant.service;

import com.myweb.restaurant.model.Item;
import com.myweb.restaurant.model.Restaurant;
import com.myweb.restaurant.repository.ItemRepository;
import com.myweb.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepo;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryImpl itemRepositoryImpl;

    @Autowired
    private MongoTemplate mongoTemplate;	//class dùng để truy vấn liên kết

    public String handleUpload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return "Please select a JSON file to upload.";
        }
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Restaurant> batch = new ArrayList<>();
            count = buildBatch(reader, count, batch);
            if (!batch.isEmpty()) {
                restaurantRepo.saveAll(batch);
            }
        } catch (Exception e) {
            return "Failed to import file: " + e.getMessage();
        }
        return "File is uploaded successfully with " + count + " documents";
    }

    public int buildBatch(BufferedReader reader, int count, List<Restaurant> batch) throws IOException {
        String line;
        ObjectMapper mapper = new ObjectMapper();

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            Restaurant r = mapper.readValue(line, Restaurant.class);
            batch.add(r);
            count++;

            if (batch.size() >= 500) {
                restaurantRepo.saveAll(batch);
                batch.clear();
            }
        }
        return count;
    }

    public Page<Restaurant> findAllPagination(Pageable pageable) {
        return restaurantRepo.findAll(pageable);
    }

    public Restaurant findById(String id) {
        return restaurantRepo.findByRestaurantId(id).orElse(null);
    }

    public Page<Restaurant> searchByName(String keyword, Pageable pageable) {
        return restaurantRepo.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public Page<Restaurant> searchRestaurant(
            String keyword,
            String borough,
            String cuisine,
            Pageable pageable
    ) {

        if (keyword == null) keyword = "";
        if (borough == null) borough = "";
        if (cuisine == null) cuisine = "";

        return restaurantRepo.searchRestaurant(
                keyword,
                borough,
                cuisine,
                pageable
        );
    }

    public Restaurant updateRestaurant(Restaurant oldObject, Restaurant newObject){
        if (Objects.nonNull(newObject.getName())){
            oldObject.setName(newObject.getName());
        }
        if (Objects.nonNull(newObject.getBorough())){
            oldObject.setBorough(newObject.getBorough());
        }
        if (Objects.nonNull(newObject.getCuisine())){
            oldObject.setCuisine(newObject.getCuisine());
        }
        return restaurantRepo.save(oldObject);
    }

    public List<HashMap> getRestaurantWithItems(String restaurantId){
        MatchOperation matchStage = Aggregation.match(Criteria.where("restaurant_id").is(restaurantId));
        LookupOperation lookupStage = LookupOperation.newLookup()
                .from("items")                 //tên của collection phụ
                .localField("restaurant_id")   //tên field của collection chính
                .foreignField("restaurant_id") //tên field của collection phụ
                .as("menuItems");              //tên field chứa giá trị liên kết của collection phụ

        Aggregation aggregation = Aggregation.newAggregation(matchStage, lookupStage);
        return mongoTemplate.aggregate(aggregation, "restaurants", HashMap.class).getMappedResults();
    }

    public List<HashMap> getItemsWithRestaurantInfo(String restaurantId) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("restaurant_id").is(restaurantId));
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("restaurants")
                .localField("restaurant_id")
                .foreignField("restaurant_id")
                .as("restaurantInfo");
        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                lookupOperation
        );
        AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "items", HashMap.class);
        return results.getMappedResults();
    }

    @Transactional
    public void updateRestaurantIdWithSave(String oldId, String newId) {
        // Step 1
        Restaurant restaurant = restaurantRepo.findFirstByRestaurantId(oldId);
        if (restaurant == null)
            throw new RuntimeException("Restaurant not found with restaurant_id: " + oldId);
        restaurant.setRestaurantId(newId);
        restaurantRepo.save(restaurant);	//lưu thông tin ID mới vào collection restaurant

        // Step 2
        itemRepositoryImpl.updateRestaurantId(oldId, newId); // update 1 lan

//        List<Item> items = itemRepository.findByRestaurantId(oldId);
//        for (Item item : items) {
//            item.setRestaurantId(newId); // update nhieu lan
//            itemRepository.save(item);	//lưu mỗi item vào collection
//        }
    }
}
