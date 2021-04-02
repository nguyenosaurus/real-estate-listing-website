package com.example.demo.controller;

import com.example.demo.model.TransactionType;
import com.example.demo.service.ITransactionTypeService;
import com.example.demo.model.Region;
import com.example.demo.service.IRegionService;
import com.example.demo.model.PropertyType;
import com.example.demo.service.IPropertyTypeService;
import com.example.demo.model.Project;
import com.example.demo.service.IProjectService;
import com.example.demo.model.Post;
import com.example.demo.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList; 

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
public class MyController {

    @Autowired
    private IRegionService regionService;

    @GetMapping("/showCity")
    public ResponseEntity<Object> findCity(Model model) {

        List<String> cities = (List<String>) regionService.findCity();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/district")
    public ResponseEntity<Object> findDistrictByCity(Model model, @RequestParam(required = false) String city) {

        List<String> districts = (List<String>) regionService.findDistrictByCity(city);

        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/ward")
    public ResponseEntity<Object> findWardByCityAndDistrict(Model model, @RequestParam(required = false) String city, @RequestParam(required = false) String district) {

        List<String> wards = (List<String>) regionService.findWardByCityAndDistrict(city, district);

        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

    @Autowired
    private ITransactionTypeService transactionTypeService;

    @GetMapping("/showTransactionType")
    public ResponseEntity<Object> findTransactionTypes(Model model) {

        List<TransactionType> transaction_types = (List<TransactionType>) transactionTypeService.findAll();

        return new ResponseEntity<>(transaction_types, HttpStatus.OK);
    }

    @Autowired
    private IProjectService projectService;

    @GetMapping("/showProject")
    public ResponseEntity<Object> findProjects(Model model) {

        List<Project> projects = (List<Project>) projectService.findAll();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Autowired
    private IPropertyTypeService propertyTypeService;

    @GetMapping("/showPropertyType")
    public ResponseEntity<Object> findPropertyTypes(Model model) {

        List<PropertyType> property_types = (List<PropertyType>) propertyTypeService.findAll();

        return new ResponseEntity<>(property_types, HttpStatus.OK);
    }

    @Autowired
    private IPostService postService;

    @GetMapping("/api/v1/posts")
    public ResponseEntity<Object> findPost(Model model, @RequestParam(name = "price.gt", required=false) String lowerPrice, @RequestParam(name = "price.lt", required=false) String upperPrice, @RequestParam(name = "area.gt", required=false) String lowerArea, @RequestParam(name = "area.lt", required=false) String upperArea, @RequestParam(required=false) String page, @RequestParam(required = false) String project, @RequestParam(required = false) String property, @RequestParam(required = false) String transaction) {

        int offset;
        if (page == null) offset = 0;
        else offset = Integer.parseInt(page);
        Page<Post> posts = (Page<Post>) postService.findAll(PageRequest.of(offset,10), null, null, null, null, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/v1/posts/{province}")
    public ResponseEntity<Object> findPost(Model model, @RequestParam(name = "price.gt", required=false) String lowerPrice, @RequestParam(name = "price.lt", required=false) String upperPrice, @RequestParam(name = "area.gt", required=false) String lowerArea, @RequestParam(name = "area.lt", required=false) String upperArea, @RequestParam(required=false) String page, @PathVariable("province") String province, @RequestParam(required = false) String project, @RequestParam(required = false) String property, @RequestParam(required = false) String transaction) {

        List<String> cities = new ArrayList<>();
        cities.add("hà nội");
        cities.add("hồ chí minh");
        cities.add("hải phòng");
        cities.add("đà nẵng");
        cities.add("cần thơ");
        Page<Post> posts;
        int offset;
        if (page == null) offset = 0;
        else offset = Integer.parseInt(page);
        if (cities.contains(province))
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), null, province, null, null, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);
        else
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), province, null, null, null, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/v1/posts/{province}/{district}")
    public ResponseEntity<Object> findPost(Model model, @RequestParam(name = "price.gt", required=false) String lowerPrice, @RequestParam(name = "price.lt", required=false) String upperPrice, @RequestParam(name = "area.gt", required=false) String lowerArea, @RequestParam(name = "area.lt", required=false) String upperArea, @RequestParam(required=false) String page, @PathVariable("province") String province, @PathVariable("district") String district, @RequestParam(required = false) String project, @RequestParam(required = false) String property, @RequestParam(required = false) String transaction) {

        List<String> cities = new ArrayList<>();
        cities.add("hà nội");
        cities.add("hồ chí minh");
        cities.add("hải phòng");
        cities.add("đà nẵng");
        cities.add("cần thơ");
        Page<Post> posts;
        int offset;
        if (page == null) offset = 0;
        else offset = Integer.parseInt(page);
        if (cities.contains(province))
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), null, province, district, null, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);
        else
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), province, district, null, null, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/v1/posts/{province}/{district}/{ward}")
    public ResponseEntity<Object> findPost(Model model, @RequestParam(name = "price.gt", required=false) String lowerPrice, @RequestParam(name = "price.lt", required=false) String upperPrice, @RequestParam(name = "area.gt", required=false) String lowerArea, @RequestParam(name = "area.lt", required=false) String upperArea, @RequestParam(required=false) String page, @PathVariable("province") String province, @PathVariable("district") String district, @PathVariable("ward") String ward, @RequestParam(required = false) String project, @RequestParam(required = false) String property, @RequestParam(required = false) String transaction) {

        List<String> cities = new ArrayList<>();
        cities.add("hà nội");
        cities.add("hồ chí minh");
        cities.add("hải phòng");
        cities.add("đà nẵng");
        cities.add("cần thơ");
        Page<Post> posts;
        int offset;
        if (page == null) offset = 0;
        else offset = Integer.parseInt(page);
        if (cities.contains(province))
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), null, province, district, ward, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);
        else
            posts = (Page<Post>) postService.findAll(PageRequest.of(offset, 10), province, district, null, ward, project, property, transaction, lowerPrice, upperPrice, lowerArea, upperArea);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}