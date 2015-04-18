package hr.controller;

import hr.service.DepartmentService;
import hr.service.EmployeeService;
import hr.service.JobService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.jboss.arquillian.core.spi.Manager;
import org.primefaces.context.RequestContext;

import util.JSFUtil;
import hr.domain.Department;
import hr.domain.Employee;
import hr.domain.Job;
import hr.domain.Location;


@Named("employeeBean")
@ViewScoped
public class EmployeeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmployeeService employeeService;
	@Inject
	private DepartmentService departmentService;
	@Inject
	private JobService jobService;

	private Employee currentEmployee = new Employee();
	private List<Employee> employees;
	private List<Employee> filteredEmployees;
	@NotNull(message="Please select the Job for the Employee.")
	private String selectedJobId = null;
	@NotNull(message="Please select the Department for the Employee.")
	private int selectedDepartmentId;
	@NotNull(message="Please select the Manager for the Employee.")
	private int selectedManagerId;
	public EmployeeController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			employees = employeeService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving employees: " + e.getMessage());
		}
	}
	public void addEmployee(){
		try{
			Department employeeDepartment = departmentService.findById(selectedDepartmentId);
			currentEmployee.setEmployeeDepartment(employeeDepartment);
			Employee employeeManager = employeeService.findById(selectedManagerId);
			currentEmployee.setEmployeeManager(employeeManager);
			Job employeeJob = jobService.findById(selectedJobId);
			currentEmployee.setEmployeeJob(employeeJob);
			if(currentEmployee.getSalary().compareTo(currentEmployee.getEmployeeJob().getMinimumSalary()) >= 0 && currentEmployee.getSalary().compareTo(currentEmployee.getEmployeeJob().getMaximumSalary()) <= 0){				
				employeeService.createEmployee(currentEmployee);
				JSFUtil.addInfoMessage(currentEmployee.toString());
				RequestContext context = RequestContext.getCurrentInstance();
				context.addCallbackParam("success", true);
				currentEmployee = new Employee();//may want to set this to null as per instructions Assign15.5
			}else{
				JSFUtil.addErrorMessage("Add employee failed: Salary is not within acceptable range for that Job Title.");
			}
		}catch(Exception e){
			JSFUtil.addErrorMessage("Add employee failed: " + e.getMessage());
		}
		
	}
	public List<Employee> getEmployees() {
		return employees;
	}

	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
	
	public String getSelectedJobId() {
		return selectedJobId;
	}

	public void setSelectedJobId(String selectedJobId) {
		this.selectedJobId = selectedJobId;
	}

	public int getSelectedDepartmentId() {
		return selectedDepartmentId;
	}

	public void setSelectedDepartmentId(int selectedDepartmentId) {
		this.selectedDepartmentId = selectedDepartmentId;
	}

	public int getSelectedManagerId() {
		return selectedManagerId;
	}

	public void setSelectedManagerId(int selectedManagerId) {
		this.selectedManagerId = selectedManagerId;
	}

	public List<SelectItem> getDepartmentOptions(){
		List<SelectItem> departmentOptions = new ArrayList<SelectItem>();
		try{
			List<Department> departments = departmentService.findAll();
			departmentOptions.add(new SelectItem("", "[select a Department]"));
			departmentOptions.add(new SelectItem("", "-------------------"));
			for(Department department : departments){
				SelectItem item = new SelectItem();
				item.setValue(department.getId());
				item.setLabel(department.getName());
				departmentOptions.add(item);
			}
		}catch(Exception e){
			JSFUtil.addErrorMessage(e.getMessage());
		}
		return departmentOptions;
	}
	public List<SelectItem> getJobOptions(){
		List<SelectItem> jobOptions = new ArrayList<SelectItem>();
		try{
			List<Job> jobs = jobService.findAll();
			jobOptions.add(new SelectItem("", "[select a Job]"));
			jobOptions.add(new SelectItem("", "-------------------"));
			for(Job job : jobs){
				SelectItem item = new SelectItem();
				item.setValue(job.getId());
				item.setLabel(job.getTitle());
				jobOptions.add(item);
			}
		}catch(Exception e){
			JSFUtil.addErrorMessage(e.getMessage());
		}
		return jobOptions;
	}
	public List<SelectItem> getManagerOptions(){
		List<SelectItem> managerOptions = new ArrayList<SelectItem>();
		try{
			List<Employee> managers = employeeService.findAllManagers();
			managerOptions.add(new SelectItem("", "[select a Manager]"));
			managerOptions.add(new SelectItem("", "-------------------"));
			for(Employee manager : managers){
				SelectItem item = new SelectItem();
				item.setValue(manager.getId());
				item.setLabel(manager.getFirstName() + ' ' + manager.getLastName() + ", " + manager.getEmployeeJob().getTitle());
				managerOptions.add(item);
			}
		}catch(Exception e){
			JSFUtil.addErrorMessage(e.getMessage());
		}
		return managerOptions;
	}
}
