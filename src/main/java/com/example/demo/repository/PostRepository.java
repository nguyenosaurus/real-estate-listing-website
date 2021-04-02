package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
	
}