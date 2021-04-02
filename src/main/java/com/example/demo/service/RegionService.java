package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.repository.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService implements IRegionService {

    @Autowired
    private RegionRepository repository;

    @Override
    public List<String> findCity() {

        List<String> cities = (List<String>) repository.findCity();

        return cities;
    }

    @Override
    public List<String> findDistrictByCity(String city) {

        List<String> districts = (List<String>) repository.findDistrictByCity(city);

        return districts;
    }

    @Override
    public List<String> findWardByCityAndDistrict(String city, String district) {

        List<String> wards = (List<String>) repository.findWardByCityAndDistrict(city, district);

        return wards;
    }
}