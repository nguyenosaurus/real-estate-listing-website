package com.example.demo.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "post")
public class Post implements java.io.Serializable{

    private String url;
    private Float price;
    private String price_unit;
    private Float area;
    private String num_bedrooms;
    private String num_bathrooms;
    private String created_date;
    private String expired_date;
    private String num_floors;
    private String floorth;
    private String direction;
    private String legal;
    private String front;
    private String alley;
    private String surrounding;
    private String surrounding_characteristics;
    private String position;
    private String internal_facility;
    private Address address;
    private Project project;
    private PropertyType propertyType;
    private TransactionType transactionType;
    private Author author;

    public Post() {
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)
	public String getUrl() {
		return this.url;
	}

    public void setUrl(String url) {
		this.url = url;
	}

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPrice_unit() {
        return this.price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public Float getArea() {
        return this.area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getNum_bedrooms() {
        return this.num_bedrooms;
    }

    public void setNum_bedrooms(String num_bedrooms) {
        this.num_bedrooms = num_bedrooms;
    }

    public String getNum_bathrooms() {
        return this.num_bathrooms;
    }

    public void setNum_bathrooms(String num_bathrooms) {
        this.num_bathrooms = num_bathrooms;
    }

    public String getCreated_date() {
        return this.created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getExpired_date() {
        return this.expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getNum_floors() {
        return this.num_floors;
    }

    public void setNum_floors(String num_floors) {
        this.num_floors = num_floors;
    }

    public String getFloorth() {
        return this.floorth;
    }

    public void setFloorth(String floorth) {
        this.floorth = floorth;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLegal() {
        return this.legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }
    
    public String getFront() {
        return this.front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getAlley() {
        return this.alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getSurrounding() {
        return this.surrounding;
    }

    public void setSurrounding(String surrounding) {
        this.surrounding = surrounding;
    }

    public String getSurrounding_characteristics() {
        return this.surrounding_characteristics;
    }

    public void setSurrounding_characteristics(String surrounding_characteristics) {
        this.surrounding_characteristics = surrounding_characteristics;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInternal_facility() {
        return this.internal_facility;
    }

    public void setInternal_facility(String internal_facility) {
        this.internal_facility = internal_facility;
    }

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addr_id", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_type_id", nullable = false)
	public PropertyType getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_type_id", nullable = false)
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
}