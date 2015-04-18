package hr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name="EMPLOYEES")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="EMPLOYEES_SEQ_GEN")
	@SequenceGenerator(name="EMPLOYEES_SEQ_GEN",sequenceName="EMPLOYEES_SEQ",allocationSize=1)
	private int id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="HIRE_DATE")
	private Date hireDate;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private Job employeeJob;
	
	@Column(name="SALARY")
	private BigDecimal salary;
	
	@Column(name="COMMISSION_PCT")
	private BigDecimal commissionPercentage;
	
	@ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private Employee employeeManager;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department employeeDepartment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getEmployeeJob() {
		return employeeJob;
	}

	public void setEmployeeJob(Job employeeJob) {
		this.employeeJob = employeeJob;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}	

	public Employee getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(Employee employeeManager) {
		this.employeeManager = employeeManager;
	}

	public Department getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(Department employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public Employee() {
		super();
	}
	
}
