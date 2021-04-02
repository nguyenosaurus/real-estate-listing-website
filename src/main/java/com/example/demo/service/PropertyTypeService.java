package com.example.demo.service;

import com.example.demo.model.PropertyType;
import com.example.demo.repository.PropertyTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTypeService implements IPropertyTypeService {

    @Autowired
    private PropertyTypeRepository repository;

    @Override
    public List<PropertyType> findAll() {

        List<PropertyType> property_types = (List<PropertyType>) repository.findAll();

        return property_types;
    }
}