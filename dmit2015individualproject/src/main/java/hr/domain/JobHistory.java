package hr.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOB_HISTORY")
public class JobHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee jobHistoryEmployee;
	
	@Id
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private Job jobHistoryJob;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department jobHistoryDepartment;
	
	
	public Employee getJobHistoryEmployee() {
		return jobHistoryEmployee;
	}
	public void setJobHistoryEmployee(Employee jobHistoryEmployee) {
		this.jobHistoryEmployee = jobHistoryEmployee;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Job getJobHistoryJob() {
		return jobHistoryJob;
	}
	public void setJobHistoryJob(Job jobHistoryJob) {
		this.jobHistoryJob = jobHistoryJob;
	}
	public Department getJobHistoryDepartment() {
		return jobHistoryDepartment;
	}
	public void setJobHistoryDepartment(Department jobHistoryDepartment) {
		this.jobHistoryDepartment = jobHistoryDepartment;
	}
	
	
	public JobHistory() {
		super();
	}
	
}
