package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.Job;

@Stateless
public class JobService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Job> findAll() {
		return entityManager.createQuery("from Job").getResultList();
	}
	
}
