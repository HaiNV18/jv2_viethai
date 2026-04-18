package com.myweb.mongo.controller;

import com.myweb.mongo.model.Notification;
import com.myweb.mongo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    public NotificationRepository repository;

    @Autowired
    private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @GetMapping("/debug")
    public Object debug() {
        return mongoTemplate.getDb().getName();
    }

    @GetMapping("")
    public List<Notification> getAllNotification() {
        return repository.findAll();
    }

    @GetMapping("/{accountId}")
    public List<Notification> getAllNotificationByAccountId(@PathVariable String accountId) {
        List<Notification> listNoti = repository.findByAccountId(accountId);

        // Cach 1: dung vong lap
//        List<Notification> result = new ArrayList<>();
//        for (int i=0; i<listNoti.size(); i++) {
//            Notification item = listNoti.get(i);
//            if (item.isRead() == false) {
//                item.setMessage("Code from Spring Boot");
//                result.add(item);
//            }
//        }

        // Cach 2: dung Java Stream
        List<Notification> result = listNoti.stream()
                                    .filter(item -> item.isRead() == false)
                                    .map(item -> {
                                        item.setMessage("Code from Spring Boot");
                                        return item;
                                    })
                                    .collect(Collectors.toList());

        return result;
    }
}
