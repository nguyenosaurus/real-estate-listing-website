package com.example.demo.repository;

import com.example.demo.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    @Query(value="select distinct city from region", nativeQuery=true)
	public List<String> findCity();

    @Query(value="select distinct district from region where city = :city", nativeQuery=true)
	public List<String> findDistrictByCity(@Param("city") String city);

    @Query(value="select distinct ward from region where city = :city and district = :district", nativeQuery=true)
	public List<String> findWardByCityAndDistrict(@Param("city") String city, @Param("district") String district);
}