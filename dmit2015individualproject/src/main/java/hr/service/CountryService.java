package hr.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hr.domain.Country;
import hr.domain.Region;

@Stateless
public class CountryService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Country> findAll() {
		return entityManager.createQuery("FROM Country c ORDER BY c.name").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> findByRegion(Region region) {
		Query query = entityManager.createQuery("FROM Country c WHERE c.countryRegion = :regionValue");
		query.setParameter("regionValue", region);
		return query.getResultList();
	}
	
	public Country findById(String id) {
		Country country = null;
		if( id != null ) {
			country = entityManager.find(Country.class, id);
		}
		return country;
	}
	
}
