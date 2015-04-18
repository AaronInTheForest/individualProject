package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.domain.Department;
import hr.domain.Employee;

@Stateless
public class EmployeeService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		return entityManager.createQuery("from Employee where employeeDepartment = :departmentValue")
				.setParameter("departmentValue", department)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		return entityManager.createQuery("FROM Employee e ORDER BY e.lastName, e.firstName").getResultList();
	}
	public void createEmployee(Employee employee){
		entityManager.persist(employee);
	}
}
