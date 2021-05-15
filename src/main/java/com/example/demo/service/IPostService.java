package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Example;

public interface IPostService {
	public Page<Post> findAll(Pageable pageable, String addrProvince, String addrCity, String addrDistrict, String addrWard, String project, String property, String transaction, String lowerPrice, String upperPrice, String lowerArea, String upperArea);
	public Specification<Post> getSpecFromPricesAndExample(Integer lowerPrice, Integer upperPrice, Integer lowerArea, Integer upperArea, Example<Post> example);
	public Post findById(Long id);
}