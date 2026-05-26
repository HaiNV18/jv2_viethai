package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.model.Logging;
import com.myweb.mongo_anime.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Autowired
    public LoggingRepository loggerRepo;

    public Logging save(Logging log) {
        return loggerRepo.save(log);
    }
}
