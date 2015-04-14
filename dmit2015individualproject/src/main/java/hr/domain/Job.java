package hr.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="JOBS")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_ID")
	private String id;
	
	@Column(name="JOB_TITLE")
	private String title;
	
	@Column(name="MIN_SALARY")
	private BigDecimal minimumSalary;
	
	@Column(name="MAX_SALARY")
	private BigDecimal maximumSalary;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getMinimumSalary() {
		return minimumSalary;
	}
	public void setMinimumSalary(BigDecimal minimumSalary) {
		this.minimumSalary = minimumSalary;
	}
	public BigDecimal getMaximumSalary() {
		return maximumSalary;
	}
	public void setMaximumSalary(BigDecimal maximumSalary) {
		this.maximumSalary = maximumSalary;
	}
	
	
	public Job() {
		super();
	}	
}
