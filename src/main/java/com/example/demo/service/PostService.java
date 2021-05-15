package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;
import com.example.demo.model.TransactionType;
import com.example.demo.model.PropertyType;
import com.example.demo.model.Project;
import com.example.demo.model.Post;
import com.example.demo.model.Address;
import com.example.demo.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;

@Transactional
@Service("postService")
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public Specification<Post> getSpecFromPricesAndExample(
		Integer lowerPrice, Integer upperPrice, Integer lowerArea, Integer upperArea, Example<Post> example) {

			return (Specification<Post>) (root, query, builder) -> {
				final List<Predicate> predicates = new ArrayList<>();

				if (lowerPrice != null) {
					predicates.add(builder.greaterThan(root.get("price"), lowerPrice));
				}
				if (upperPrice != null) {
					predicates.add(builder.lessThan(root.get("price"), upperPrice));
				}
				if (lowerArea != null) {
					predicates.add(builder.greaterThan(root.get("area"), lowerArea));
				}
				if (upperArea != null) {
					predicates.add(builder.lessThan(root.get("area"), upperArea));
				}
				predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			};
		};

	@Override
	public Page<Post> findAll(Pageable pageable, String addrProvince, String addrCity, String addrDistrict, String addrWard, String project, String property, String transaction, String lowerPrice, String upperPrice, String lowerArea, String upperArea) {
		Post post = new Post();
		TransactionType tr = new TransactionType();
		Address a = new Address();
		Project proj = new Project();
		PropertyType p = new PropertyType();

		tr.setTransaction_type(transaction);
		proj.setProject(project);
		p.setPropertyType(property);
		a.setAddrProvince(addrProvince);
		a.setAddrCity(addrCity);
		a.setAddrDistrict(addrDistrict);
		a.setAddrWard(addrWard);

		post.setAddress(a);
		if (transaction != null && !transaction.isEmpty()) post.setTransactionType(tr);
		if (project != null && !project.isEmpty()) post.setProject(proj);
		if (property != null && !property.isEmpty()) post.setPropertyType(p);
		ExampleMatcher matcher = ExampleMatcher.matching()
		.withIgnoreNullValues();
		Example<Post> exampleQuery = Example.of(post, matcher);
		return postRepository.findAll(getSpecFromPricesAndExample(lowerPrice != null ? Integer.valueOf(Integer.parseInt(lowerPrice)) : null, upperPrice != null ? Integer.valueOf(Integer.parseInt(upperPrice)) : null, lowerArea != null ? Integer.valueOf(Integer.parseInt(lowerArea)) : null, upperArea != null ? Integer.valueOf(Integer.parseInt(upperArea)) : null, exampleQuery), pageable);
	}

}