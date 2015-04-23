package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.Region;

@Stateless
public class RegionService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Region> findAll() {
		return entityManager.createQuery("from Region r order by r.name").getResultList();
	}
}
