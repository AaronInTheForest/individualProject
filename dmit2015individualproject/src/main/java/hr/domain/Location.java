package hr.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LOCATIONS")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOCATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="LOCATIONS_SEQ_GEN")
	@SequenceGenerator(name="LOCATIONS_SEQ_GEN",sequenceName="LOCATIONS_SEQ",allocationSize=1)
	private int id;
	
	@Column(name="STREET_ADDRESS", length=40)
	private String streetAddress;
	
	@Column(name="POSTAL_CODE", length=12)
	private String postalCode;
	
	@Column(name="CITY", length=30, nullable=false)
	private String city;
	
	@Column(name="STATE_PROVINCE", length=25)
	private String stateProvince;
	
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="COUNTRY_ID")
	private Country locationCountry;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public Country getLocationCountry() {
		return locationCountry;
	}

	public void setLocationCountry(Country locationCountry) {
		this.locationCountry = locationCountry;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
