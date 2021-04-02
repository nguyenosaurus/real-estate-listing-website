package com.example.demo.service;

import com.example.demo.model.Region;

import java.util.List;

public interface IRegionService {

    List<String> findCity();
    List<String> findDistrictByCity(String city);
    List<String> findWardByCityAndDistrict(String city, String district);
}