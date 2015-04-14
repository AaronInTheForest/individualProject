package hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.JobHistory;
import hr.service.JobHistoryService;

@Named("jobHistoryBean")
@RequestScoped
public class JobHistoryController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private JobHistoryService jobHistoryService;
	
	private List<JobHistory> jobHistories;

	public JobHistoryController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			jobHistories = jobHistoryService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving job histories: " + e.getMessage());
		}
	}

	public List<JobHistory> getJobHistories() {
		return jobHistories;
	}

}
