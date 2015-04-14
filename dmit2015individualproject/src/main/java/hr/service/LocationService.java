package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.Country;
import hr.domain.Location;

@Stateless
public class LocationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Location> findByCountry(Country country) {
		return entityManager.createQuery("from Location l where ;.locationCountry = :countryValue")
				.setParameter("countryValue", country)
				.getResultList();
	}
	
	public void add(Location location) {
		entityManager.persist(location);
	}
	
	public Location findById(int id) {
		return entityManager.find(Location.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Location> findAll() {
		return entityManager.createQuery("from Location").getResultList();
	}
	
}
