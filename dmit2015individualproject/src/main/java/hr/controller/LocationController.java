package hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Location;
import hr.service.LocationService;

@Named("locationBean")
@ViewScoped
public class LocationController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LocationService locationService;
	
	private List<Location> locations;
	private List<Location> filteredLocations;
	
	public LocationController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			locations = locationService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving locations: " + e.getMessage());
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public List<Location> getFilteredLocations() {
		return filteredLocations;
	}

	public void setFilteredLocations(List<Location> filteredLocations) {
		this.filteredLocations = filteredLocations;
	}

}
