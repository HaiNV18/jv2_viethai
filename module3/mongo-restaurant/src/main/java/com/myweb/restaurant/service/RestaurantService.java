package com.myweb.restaurant.service;

import com.myweb.restaurant.model.Restaurant;
import com.myweb.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepo;

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

}
