package com.example.demo.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "region")
public class Region implements java.io.Serializable {

    @Column(name="region_id")
    private Integer regionId;
    
    private String city;
    private String district;
    private String ward;

    public Region() {
    }

    public Region(Integer regionId, String city, String district, String ward) {

        this.regionId = regionId;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}