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
@Table(name = "address")
public class Address implements java.io.Serializable {

    private Integer addr_id;

    @Column(name="addr_province")
    private String addrProvince;
    
    @Column(name="addr_city")
    private String addrCity;

    @Column(name="addr_district")
    private String addrDistrict;

    @Column(name="addr_ward")
    private String addrWard;
    private String addr_street;
    private Double longitude;
    private Double latitude;
    private Set<Post> posts = new HashSet<Post>(0);

    public Address() {
    }

    public Address(Integer addr_id, String addrProvince, String addrCity, String addrDistrict, String addrWard, String addr_street, Double longitude, Double latitude) {

        this.addr_id = addr_id;
        this.addrProvince = addrProvince;
        this.addrCity = addrCity;
        this.addrDistrict = addrDistrict;
        this.addrWard = addrWard;
        this.addr_street = addr_street;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)
    public Integer getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(Integer addr_id) {
        this.addr_id = addr_id;
    }

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrDistrict() {
        return addrDistrict;
    }

    public void setAddrDistrict(String addrDistrict) {
        this.addrDistrict = addrDistrict;
    }

    public String getAddrWard() {
        return addrWard;
    }

    public void setAddrWard(String addrWard) {
        this.addrWard = addrWard;
    }

    public String getAddr_street() {
        return addr_street;
    }

    public void setAddr_street(String addr_street) {
        this.addr_street = addr_street;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}