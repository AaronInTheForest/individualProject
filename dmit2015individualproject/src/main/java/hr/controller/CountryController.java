package hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Country;
import hr.service.CountryService;

@Named("countryBean")
@ViewScoped
public class CountryController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CountryService countryService;
	
	private List<Country> countries;
	private List<Country> filteredCountries;

	public CountryController() {
		
	} 
	
	@PostConstruct
	public void init() {
		try {
			countries = countryService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving countries: " + e.getMessage());
		}
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setFilteredCountries(List<Country> filteredCountries) {
		this.filteredCountries = filteredCountries;
	}

	public List<Country> getFilteredCountries() {
		return filteredCountries;
	}

	public SelectItem[] getCountryOptions() {
		int countryCount = countries.size();
		int index = 0;
		SelectItem[] options = new SelectItem[ countryCount + 1 ];
		options[ index++ ] = new SelectItem("","Select");
		for(Country country : countries) {
			options[index++] = new SelectItem( country.getId(), country.getName() );
		}
		return options;
	}
	
}
