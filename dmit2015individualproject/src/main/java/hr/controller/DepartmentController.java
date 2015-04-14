package hr.controller;

import hr.service.DepartmentService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Department;

@Named("departmentBean")
@ViewScoped
public class DepartmentController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private DepartmentService departmentService;
	
	private List<Department> departments;
	private List<Department> filteredDepartments;

	public DepartmentController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			departments = departmentService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving departments: " + e.getMessage());
		}
	}
	
	public List<Department> getDepartments() {
		return departments;
	}

	public List<Department> getFilteredDepartments() {
		return filteredDepartments;
	}

	public void setFilteredDepartments(List<Department> filteredDepartments) {
		this.filteredDepartments = filteredDepartments;
	}
	
	public SelectItem[] getDepartmentOptions() {
		int optionCount = departments.size();
		SelectItem[] options = new SelectItem[ optionCount + 1 ];
		int index = 0;
		options[index++] = new SelectItem("", "Select");
		for(Department department : departments) {
			options[index++] = new SelectItem( department.getId(), department.getName() );
		}
		return options;
	}
}
