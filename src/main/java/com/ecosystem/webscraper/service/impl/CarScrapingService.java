package com.ecosystem.webscraper.service.impl;

import com.ecosystem.webscraper.dto.CarDetails;

import java.io.IOException;

public interface CarScrapingService {
    CarDetails scrapeCarDetails(String url) throws IOException;
}
