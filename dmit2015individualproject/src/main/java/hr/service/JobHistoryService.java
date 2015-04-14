package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.JobHistory;

@Stateless
public class JobHistoryService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<JobHistory> findAll() {
		return entityManager.createQuery("from JobHistory").getResultList();
	}
	
}
