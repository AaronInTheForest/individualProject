package hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import util.JSFUtil;
import hr.domain.Job;
import hr.service.JobService;

@Named("jobBean")
@ViewScoped
public class JobController implements Serializable {
	private static final long serialVersionUID = -7627695462205437369L;

	@Inject
	private JobService jobService;
	
	private List<Job> jobs;

	public JobController() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			jobs = jobService.findAll();
		} catch( Exception e) {
			JSFUtil.addErrorMessage("Error retreiving jobs: " + e.getMessage());
		}
	}

	public List<Job> getJobs() {
		return jobs;
	}
}
