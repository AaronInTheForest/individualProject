package hr.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.Department;
import hr.domain.Location;

@Stateless
public class DepartmentService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Department> findByLocation(Location location) {
		return entityManager.createQuery("FROM Department d WHERE d.location = :locationValue")
				.setParameter("locationValue", location)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		return entityManager.createQuery("FROM Department d ORDER BY d.name").getResultList();
	}
	
}
