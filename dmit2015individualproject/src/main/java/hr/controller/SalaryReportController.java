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

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

import util.JSFUtil;

@ViewScoped
@Named("SalaryController")
public class SalaryReportController implements Serializable{
	//Variables and Properties
	private static final Logger logger= Logger.getLogger(
			SalaryReportController.class.getName());
	private static final long serialVersionUID = 1L;
	private PieChartModel regionChartModel = null;
	private BarChartModel countryChartModel = null;
	private HorizontalBarChartModel departmentChartModel = null;
	private HorizontalBarChartModel jobTitleChartModel = null;
	private List<Location> locations = null;
	private List<Region> regions= null;
	private List<Country> countries= null;
	private List<Department> departments= null;
	private List<Job> jobs= null;
	private List<Employee> employees = null;
	
	@PersistenceContext
	private EntityManager entityManager;
	//Services
	@Inject
	private RegionService regionService;
	@Inject
	private CountryService countryService;
	@Inject
	private DepartmentService departmentService;
	@Inject
	private JobService jobService;
	@Inject
	private LocationService locationService;
	@Inject
	private EmployeeService employeeService;
	//Methods
	public void createRegionChartModel(){
		logger.info("entering createRegionChartModel()");
		//clear data
		countryChartModel = null;
		departmentChartModel = null;
		jobTitleChartModel = null;
		regions = null;
		countries = null;
		locations = null; 
		departments = null;
		employees = null;
		//create the data for the pie chart
		regionChartModel =  new PieChartModel();
		regionChartModel.setTitle("Salary by Region");
		regionChartModel.setLegendPosition("e");
		regionChartModel.setShowDataLabels(true);
		regions = new ArrayList<Region>();
		try{
			for(Region region : regionService.findAll()){
				Query query = entityManager.createQuery("SELECT SUM(salary) FROM Employee WHERE employeeDepartment.departmentLocation.locationCountry.countryRegion = :regionParam");
				query.setParameter("regionParam", region);
				BigDecimal totalSalary = (BigDecimal) query.getSingleResult();
				if(totalSalary != null){
					regionChartModel.set(region.getName(), totalSalary.doubleValue());
				}
			}//THIS WAS MY SOLUTION, SAM RECOMMENDS ANOTHER
			/*
			for(Region region : regionService.findAll()){
				BigDecimal regionSalaryTotal = new BigDecimal(0);
				countries = countryService.findByRegion(region);
				for(Country country : countries){
					locations = locationService.findByCountry(country);
					for(Location location : locations){
						departments = departmentService.findByLocation(location);
						for(Department department : departments){
							employees = employeeService.findByDepartment(department);
							for(Employee employee : employees){
								regionSalaryTotal.add(employee.getSalary());
							}
						}
					}
				}
				
				double doubleRegionTotalSalary = regionSalaryTotal.doubleValue();
				if(doubleRegionTotalSalary > 0){
					regionChartModel.set(region.getName(), doubleRegionTotalSalary);
				}
			}*/
		}catch(Exception e){
			regionChartModel = null;
			JSFUtil.addErrorMessage("Error creating region chart with exception: " + e.getMessage());
		}
	}
	public void createCountryChartModel(){
		logger.info("entering createCartesianModel()");
		//clear data
		regionChartModel = null;
		departmentChartModel = null;
		jobTitleChartModel = null;
		regions = null;
		countries = null;
		locations = null; 
		departments = null;
		employees = null;
		//create data for the BarChart
		countryChartModel = new BarChartModel();
		countryChartModel.setTitle("Salary by Country");
		ChartSeries countrySeries = new ChartSeries();
		countrySeries.setLabel("Country Name");
		try{
			for(Country country : countryService.findAll()){
				Query query = entityManager.createQuery("SELECT SUM(salary) FROM Employee WHERE employeeDepartment.departmentLocation.locationCountry = :countryParam");
				query.setParameter("countryParam", country);
				BigDecimal totalSalary = (BigDecimal) query.getSingleResult();				//do not include categories for which there are no expenses
				if(totalSalary != null){
					countrySeries.set(country.getName(), totalSalary);
				}
			}
			countryChartModel.addSeries(countrySeries);
		}catch(Exception e){
			countryChartModel = null;
			JSFUtil.addErrorMessage("Error creating the bar chart with exception: " + e.getMessage());
		}
	}
	public void createDepartmentChartModel(){
		logger.info("entering createCartesianModel()");
		//clear data
		regionChartModel = null;
		countryChartModel = null;
		jobTitleChartModel = null;
		regions = null;
		countries = null;
		locations = null; 
		departments = null;
		employees = null;
		//create data for the BarChart
		departmentChartModel = new HorizontalBarChartModel();
		departmentChartModel.setTitle("Salary by Department");
		ChartSeries departmentSeries = new ChartSeries();
		departmentSeries.setLabel("Department Name");
		try{
			for(Department department : departmentService.findAll()){
				Query query = entityManager.createQuery("SELECT SUM(salary) FROM Employee WHERE employeeDepartment = :departmentParam");
				query.setParameter("departmentParam", department);
				BigDecimal totalSalary = (BigDecimal) query.getSingleResult();				//do not include categories for which there are no expenses
				if(totalSalary != null){
					departmentSeries.set(department.getName(), totalSalary);
				}
			}
			departmentChartModel.addSeries(departmentSeries);
		}catch(Exception e){
			departmentChartModel = null;
			JSFUtil.addErrorMessage("Error creating the bar chart with exception: " + e.getMessage());
		}
	}
	
	
	//Getters and Setters
	public PieChartModel getRegionChartModel() {
		return regionChartModel;
	}
	public void setRegionChartModel(PieChartModel regionChartModel) {
		this.regionChartModel = regionChartModel;
	}
	public BarChartModel getCountryChartModel() {
		return countryChartModel;
	}
	public void setCountryChartModel(BarChartModel countryChartModel) {
		this.countryChartModel = countryChartModel;
	}
	public BarChartModel getDepartmentChartModel() {
		return departmentChartModel;
	}
	public void setDepartmentChartModel(HorizontalBarChartModel departmentChartModel) {
		this.departmentChartModel = departmentChartModel;
	}
	public BarChartModel getJobTitleChartModel() {
		return jobTitleChartModel;
	}
	public void setJobTitleChartModel(HorizontalBarChartModel jobTitleChartModel) {
		this.jobTitleChartModel = jobTitleChartModel;
	}
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
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
}
