package hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	@SuppressWarnings("unchecked")
	public Employee findById(int id) {
		Query query = entityManager.createQuery("FROM Employee e WHERE employee_id = :idValue ORDER BY e.lastName, e.firstName");
		query.setParameter("idValue", id);
		Employee employee = (Employee) query.getSingleResult();
		return employee;
	}
	@SuppressWarnings("unchecked")
	public List<Employee> findAllManagers() {
		return entityManager.createQuery("FROM Employee e WHERE job_Id IN ('FI_MGR','AC_MGR','SA_MAN','PU_MAN','ST_MAN','MK_MAN')  ORDER BY e.lastName, e.firstName").getResultList();
	}
	public void createEmployee(Employee employee){
		entityManager.persist(employee);
	}
}
