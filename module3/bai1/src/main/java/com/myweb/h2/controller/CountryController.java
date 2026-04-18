package com.myweb.h2.controller;

import com.myweb.h2.model.Country;
import com.myweb.h2.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping("")
    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

}
