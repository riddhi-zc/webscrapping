package com.ecosystem.webscraper.service;

import com.ecosystem.webscraper.dto.CarDetails;
import com.ecosystem.webscraper.service.impl.CarScrapingService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CarScrapingServiceImpl implements CarScrapingService {

    public CarDetails scrapeCarDetails(String url) throws IOException {
        CarDetails carDetails = new CarDetails();
        Document doc = Jsoup.connect(url).get();
        log.info("doc{}",doc.outerHtml());

        Element ucrVdpTabs = doc.selectFirst("ul.ucrVdpTabs");
        log.info("doc 2{}",ucrVdpTabs.outerHtml());
        // Add the active class to all li elements within the ul
        if (ucrVdpTabs != null) {

            Elements tabs = ucrVdpTabs.select("li");
            log.info("doc 3{}",tabs.outerHtml());
            for (Element tab : tabs) {
                tab.addClass("active");
            }
        }
        Elements els=doc.select("div.outer-card-container");
        log.info("eachList{}",els);

        Elements items = doc.select("ul.detailsList li");

        //This iterate the OverView
        for (Element item : items) {
            String label = item.select(".label").text();
            String value = item.select(".value-text").text();
            System.out.println(label + ": " + value);
            switch (label) {
                case "Registration Year":
                    carDetails.setRegistrationYear(value);
                    break;
                case "Insurance":
                    carDetails.setInsurance(value);
                    break;
                case "Fuel Type":
                    carDetails.setFuelType(value);
                    break;
                case "Seats":
                    carDetails.setSeats(value);
                case "Kms Driven":
                    carDetails.setKmsDriven(value);
                    break;
                case "RTO":
                    carDetails.setRto(value);
                    break;
                case "Ownership":
                    carDetails.setOwnership(value);
                    break;
                case   "Engine Displacement":
                    carDetails.setEngineDisplacement(value);
                    break;
                case "Transmission":
                    carDetails.setTransmission(value);
                    break;
                case "Year of Manufacture" :
                    carDetails.setYearManufacture(Long.valueOf(value));
                    break;
            }
        }
        Elements hoverEls=doc.select("div.outer-card-container .specsCard");
        Element specsCard = doc.getElementById("tab-id-vdpCard-carFeatures");
        log.info("Specs Card{}",hoverEls.outerHtml());
        for (Element spec : els) {
            Elements labelLi=spec.select(".each-list");
            Elements labelText=labelLi.select("label-text");
            String label = labelText.select(".label").text();
            String value = labelText.select(".value-text").text();
            log.info("label :{}",label);
            log.info("value :{}",value);

        }
        carDetails.setName(doc.select(".vehicleName").text());
        carDetails.setPrice(doc.select(".vehiclePrice").text());
        return carDetails;
    }
    private String extractValue(Document document, String label) {
        Elements elements = document.select(".each-list:contains(" + label + ")");
        for (Element element : elements) {
            if (element.text().contains(label)) {
                return element.select(".value-text").text();
            }
        }
        return null;
    }
}

