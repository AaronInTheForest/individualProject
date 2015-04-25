package hr.controller;

import hr.domain.Country;
import hr.domain.Department;
import hr.domain.Employee;
import hr.domain.Job;
import hr.domain.Location;
import hr.domain.Region;
import hr.service.CountryService;
import hr.service.DepartmentService;
import hr.service.EmployeeService;
import hr.service.JobService;
import hr.service.LocationService;
import hr.service.RegionService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.JSFUtil;

@ViewScoped
@Named("RegionTableController")
public class RegionTableController implements Serializable{
	//Variables and Properties
	private static final long serialVersionUID = 1L;
	
	//Injection services
	@Inject
	private RegionService regionService;
	@Inject
	private CountryService countryService;
	@Inject
	private LocationService locationService;
	@Inject
	private DepartmentService departmentService;
	@Inject
	private EmployeeService employeeService;
	
	//Lists
	private List<Region> regions = null;
	private List<Country> countries = null;
	private List<Location> locations = null;
	private List<Department> departments = null;
	private List<Employee> employees = null;

	@PostConstruct
	public void init(){
		regions = new ArrayList<Region>();
		try{
			for(Region region : regionService.findAll()){
				regions.add(region);
			}
		}catch(Exception e){
			regions = null;
			JSFUtil.addErrorMessage("Error creating summary table with exception: " + e.getMessage());
		}
	}
	public void viewCountries (Region region){
		countries = new ArrayList<Country>();
		try{
			for(Country country : countryService.findByRegion(region)){
				countries.add(country);
			}
			regions = null;
		}catch(Exception e){
			countries = null;
			JSFUtil.addErrorMessage("Error creating summary table with exception: " + e.getMessage());
		}
	}
	public void viewLocations (Country country){
		locations = new ArrayList<Location>();
		try{
			for(Location location : locationService.findByCountry(country)){
				locations.add(location);
			}
			countries = null;
		}catch(Exception e){
			locations = null;
			JSFUtil.addErrorMessage("Error creating summary table with exception: " + e.getMessage());
		}
	}
	public void viewDepartments (Location location){
		departments = new ArrayList<Department>();
		try{
			for(Department department : departmentService.findByLocation(location)){
				departments.add(department);
			}
			locations = null;
		}catch(Exception e){
			departments = null;
			JSFUtil.addErrorMessage("Error creating summary table with exception: " + e.getMessage());
		}
	}
	public void viewEmployees (Department department){
		employees = new ArrayList<Employee>();
		try{
			for(Employee employee : employeeService.findByDepartment(department)){
				employees.add(employee);
			}
			departments = null;
		}catch(Exception e){
			employees = null;
			JSFUtil.addErrorMessage("Error creating summary table with exception: " + e.getMessage());
		}
	}
//GETTERS AND SETTERS
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
