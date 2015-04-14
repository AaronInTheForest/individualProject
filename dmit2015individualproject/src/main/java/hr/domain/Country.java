package hr.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRIES")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COUNTRY_ID",length=2,unique=true, columnDefinition="CHAR(2)")
	private String id;
	
	@Column(name="COUNTRY_NAME")
	private String name;
	
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="REGION_ID")
	private Region countryRegion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getCountryRegion() {
		return countryRegion;
	}

	public void setCountryRegion(Region countryRegion) {
		this.countryRegion = countryRegion;
	}

	public Country() {
		super();
	}
		
}
