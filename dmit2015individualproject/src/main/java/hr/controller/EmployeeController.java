package hr.controller;

import hr.service.EmployeeService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Employee;


@Named("employeeBean")
@ViewScoped
public class EmployeeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmployeeService employeeService;
	
	private List<Employee> employees;
	private List<Employee> filteredEmployees;

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

	public List<Employee> getEmployees() {
		return employees;
	}

	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

}
