package com.ecosystem.webscraper.restcontroller;


import com.ecosystem.webscraper.dto.CarDetails;
import com.ecosystem.webscraper.service.impl.CarScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class CarRestController {

    @Autowired
    private CarScrapingService carScrapingService;

    @GetMapping("/scrape-car")
    public CarDetails scrapeCar(@RequestParam String url) {
        try {
            return carScrapingService.scrapeCarDetails(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}