package com.ecosystem.webscraper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDetails {
    private String name;
    private String price;
    private String transmission;
  /*  private String fuel;
    private String power;
    private String mileage;*/
    private String registrationYear;
    private  String insurance;
    private  String  fuelType;
    private  String seats;
    private  String KmsDriven;
    private String rto;
    private  String ownership;
    private String engineDisplacement;
    private  Long yearManufacture;
}
