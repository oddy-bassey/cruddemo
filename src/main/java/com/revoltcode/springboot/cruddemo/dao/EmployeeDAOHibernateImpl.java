package com.revoltcode.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
 
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revoltcode.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//Define the field for entity manager
	private EntityManager entityManager;
	
	//Setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override 
	public List<Employee> findall() {
		
		//Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the results 
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		//get the employee
		Employee employee = currentSession.get(Employee.class, theId);
		
		//return the employee
		
		return employee;
	}

	@Override 
	public void save(Employee employee) {

		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		//save object
		currentSession.saveOrUpdate(employee);
	}

	@Override 
	public void deleteById(int id) {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class); 
		
		//delete object by primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", id);
		
		theQuery.executeUpdate();
	}
}



















