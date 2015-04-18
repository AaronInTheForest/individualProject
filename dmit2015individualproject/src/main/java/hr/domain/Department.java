package hr.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPARTMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DEPARTMENTS_SEQ_GEN")
	@SequenceGenerator(name = "DEPARTMENTS_SEQ_GEN", sequenceName = "DEPARTMENTS_SEQ",allocationSize=1)
	private int id;

	@Column(name = "DEPARTMENT_NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee departmentManager;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location departmentLocation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(Employee departmentManager) {
		this.departmentManager = departmentManager;
	}

	public Location getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(Location departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public Department() {
		super();
	}

}
